document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        Swal.fire({
            title: 'Confirma a exclusão?',
            text: "Você não poderá reverter isso!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, excluir!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {

                const row = this.closest('tr');
                const colaboradorCodigo = this.dataset.colaboradorCodigo;

                if (!colaboradorCodigo) {
                    console.error("Código do colaborador não encontrado.");
                    Swal.fire('Erro', 'Código do colaborador não encontrado.', 'error');
                    return;
                }

                fetch(`/colaboradores/${colaboradorCodigo}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            row.remove();
                            Swal.fire('Excluído!', 'O colaborador foi excluído.', 'success');
                        } else {
                            Swal.fire('Erro', 'Erro ao excluir colaborador.', 'error');
                        }
                    })
                    .catch(error => {
                        Swal.fire('Erro de rede', 'Erro de rede: ' + error, 'error');
                    });
            }
        });
    });
});
