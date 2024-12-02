document.querySelectorAll('.finalizar').forEach(function(button) {
    button.addEventListener('click', function() {
        // Usa o SweetAlert para confirmação antes de ativar
        console.log("EMPRESTIMO")
        Swal.fire({
            title: 'Confirma a finalização do empréstimo?',
            text: "Você pode reverter isso a qualquer momento.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, finalizar!',
            cancelButtonText: 'Cancelar',
        }).then((result) => {
            if (result.isConfirmed) {
                // Recupera a linha <tr> que contém o botão de finalizar
                const row = this.closest('tr');

                // Recupera o código do emprestimo do atributo 'data-emprestimo-codigo'
                const emprestimoCodigo = this.dataset.emprestimoCodigo;

                // Verifica se o código do emprestimo foi recuperado corretamente
                if (!emprestimoCodigo) {
                    console.error("Código do emprestimo não encontrado.");
                    Swal.fire('Erro', 'Código do emprestimo não encontrado.', 'error');
                    return;
                }

                // Realiza a requisição para finalizar o emprestimo
                fetch(`/emprestimos/finalizar/${emprestimoCodigo}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            window.location.reload();
                        } else {
                            console.error('Erro ao finalizar emprestimo. Status:', response.status);
                            Swal.fire('Erro', 'Erro ao finalizar emprestimo.', 'error');
                        }
                    })
                    .catch(error => {
                        console.error('Erro de rede:', error);
                        Swal.fire('Erro de rede', 'Erro de rede: ' + error, 'error');
                    });
            }
        });
    });
});
