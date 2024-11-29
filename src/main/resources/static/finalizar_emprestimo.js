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

            // Verifica se o código do empréstimo foi recuperado corretamente
            if (!emprestimoCodigo) {
                console.error("Código do empréstimo não encontrado.");
                alert("Erro: código do empréstimo não encontrado.");
                return;
            }

            // Realiza a requisição para finalizar o empréstimo
            fetch(`/emprestimos/finalizar/${emprestimoCodigo}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        window.location.reload();
                    }
                })
                .catch(error => {
                    console.error('Erro de rede:', error);
                    alert('Erro de rede: ' + error);
                });
        }
    });
});
