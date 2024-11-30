document.querySelectorAll('.ativar').forEach(function(button) {
    button.addEventListener('click', function() {
        // Usa o SweetAlert para confirmação antes de ativar
        Swal.fire({
            title: 'Confirma a ativação do tipo de epi?',
            text: "Você pode reverter isso a qualquer momento.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, ativar!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                // Recupera a linha <tr> que contém o botão de ativar
                const row = this.closest('tr');

                // Recupera o código do cargo do atributo 'data-tipo-codigo'
                const tipoCodigo = this.dataset.tipoCodigo;

                // Verifica se o código do cargo foi recuperado corretamente
                if (!tipoCodigo) {
                    console.error("Código do tipo de equipamento não encontrado.");
                    Swal.fire('Erro', 'Código do tipo de equipamento não encontrado.', 'error');
                    return;
                }

                // Realiza a requisição para ativar o tipo de equipamento
                fetch(`/epis/tipo/ativar/${tipoCodigo}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            window.location.reload();
                        } else {
                            console.error('Erro ao ativar tipo de equipamento. Status:', response.status);
                            Swal.fire('Erro', 'Erro ao ativar tipo de equipamento.', 'error');
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
