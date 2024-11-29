document.querySelectorAll('.finalizar').forEach(function(button) {
    button.addEventListener('click', function() {
        Swal.fire({
            title: 'Confirma a finalização do empréstimo?',
            text: "Você pode reverter isso a qualquer momento.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, finalizar!',
            cancelButtonText: 'Cancelar',
            buttonsStyling: true,
            customClass: {
                confirmButton: 'swal2-confirm',
                cancelButton: 'swal2-cancel'
            }
        }).then((result) => {
            if (result.isConfirmed) {

                const row = this.closest('tr');
                const emprestimoCodigo = this.dataset.emprestimoCodigo;

                if (!emprestimoCodigo) {
                    console.error("Código do empréstimo não encontrado.");
                    Swal.fire('Erro', 'Código do empréstimo não encontrado.', 'error');
                    return;
                }
                fetch(`/emprestimos/finalizar/${emprestimoCodigo}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            row.remove();
                            Swal.fire('Finalizado!', 'O empréstimo foi finalizado com sucesso.', 'success');
                        } else {
                            Swal.fire('Erro', 'Erro ao finalizar empréstimo.', 'error');
                        }
                    })
                    .catch(error => {
                        Swal.fire('Erro de rede', 'Erro de rede: ' + error, 'error');
                    });
            }
        });
    });
});
