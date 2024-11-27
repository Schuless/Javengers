document.querySelectorAll('#excluir').forEach(function(button) {
    debugger;
    button.addEventListener('click', function() {
        const colaboradorId = this.dataset.colaboradorId;

        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você não poderá reverter isso!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, excluir!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                fetch(`/colaborador/${colaboradorId}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            // A exclusão foi bem-sucedida
                            console.log('Usuário excluído com sucesso.');

                            // Remove a linha da tabela após a exclusão
                            window.location.reload();
                            Swal.fire(
                                'Excluído!',
                                'O usuário foi excluído com sucesso.',
                                'success'
                            );
                        } else {
                            // A solicitação DELETE falhou
                            console.error('Erro ao excluir usuário.');
                            Swal.fire(
                                'Erro!',
                                'Erro ao excluir usuário.',
                                'error'
                            );
                        }
                    })
                    .catch(error => {
                        // Lidar com erros de rede ou outros erros
                        console.error('Erro de rede:', error);
                        Swal.fire(
                            'Erro!',
                            'Erro de rede: ' + error,
                            'error'
                        );
                    });
            }
        });
    });
});
