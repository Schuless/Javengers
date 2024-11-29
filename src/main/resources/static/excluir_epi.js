document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        // Usa o SweetAlert para confirmação antes de excluir
        Swal.fire({
            title: 'Confirma a exclusão do EPI?',
            text: "Você não poderá reverter isso!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, excluir!',
            cancelButtonText: 'Cancelar',
            buttonsStyling: true, // Usa os estilos de botão padrão do SweetAlert
            customClass: {
                confirmButton: 'swal2-confirm',
                cancelButton: 'swal2-cancel'
            }
        }).then((result) => {
            if (result.isConfirmed) {
                // Recupera a linha <tr> que contém o botão de exclusão
                const row = this.closest('tr');

                // Recupera o código do EPI do atributo 'data-epi-codigo'
                const epiCodigo = this.dataset.epiCodigo;

                // Verifica se o código do EPI foi recuperado corretamente
                if (!epiCodigo) {
                    console.error("Código do EPI não encontrado.");
                    Swal.fire('Erro', 'Código do EPI não encontrado.', 'error');
                    return;
                }

                // Realiza a requisição para excluir o EPI
                fetch(`/epis/${epiCodigo}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            console.log('EPI excluído com sucesso.');
                            row.remove();  // Remove a linha da tabela após a exclusão bem-sucedida
                            Swal.fire('Excluído!', 'O EPI foi excluído.', 'success');
                        } else {
                            console.error('Erro ao excluir EPI. Status:', response.status);
                            Swal.fire('Erro', 'Erro ao excluir EPI.', 'error');
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
