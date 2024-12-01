document.querySelectorAll('.desativar').forEach(function(button) {
    button.addEventListener('click', function() {
        // Usa o SweetAlert para confirmação antes de desativar
        Swal.fire({
            title: 'Confirma a desativação do tipo de equipametno?',
            text: "Você pode reverter isso a qualquer momento.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, desativar!',
            cancelButtonText: 'Cancelar',
            buttonsStyling: true, // Usa os estilos de botão padrão do SweetAlert
            customClass: {
                confirmButton: 'swal2-confirm',
                cancelButton: 'swal2-cancel'
            }
        }).then((result) => {
            if (result.isConfirmed) {
                // Recupera a linha <tr> que contém o botão de desativar
                const row = this.closest('tr');

                // Recupera o código do cargo do atributo 'data-tipo-codigo'
                const tipoCodigo = this.dataset.tipoCodigo;

                // Verifica se o código do tipo foi recuperado corretamente
                if (!tipoCodigo) {
                    console.error("Código do tipo de equipamento não encontrado.");
                    Swal.fire('Erro', 'Código do tipo de equipamento não encontrado.', 'error');
                    return;
                }

                // Realiza a requisição para desativar o tipo
                fetch(`/epis/tipo/desativar/${tipoCodigo}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            window.location.reload();
                        } else {
                            console.error('Erro ao desativar tipo de equipamento. Status:', response.status);
                            Swal.fire('Erro', 'Erro ao desativar tipo de equipamento.', 'error');
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
