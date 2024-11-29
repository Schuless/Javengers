document.querySelectorAll('.ativar').forEach(function(button) {
    button.addEventListener('click', function() {
        // Usa o SweetAlert para confirmação antes de ativar
        Swal.fire({
            title: 'Confirma a ativação do cargo?',
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

                // Recupera o código do cargo do atributo 'data-cargo-codigo'
                const cargoCodigo = this.dataset.cargoCodigo;

                // Verifica se o código do cargo foi recuperado corretamente
                if (!cargoCodigo) {
                    console.error("Código do cargo não encontrado.");
                    Swal.fire('Erro', 'Código do cargo não encontrado.', 'error');
                    return;
                }

                // Realiza a requisição para ativar o cargo
                fetch(`/cargos/ativar/${cargoCodigo}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            window.location.reload();
                        } else {
                            console.error('Erro ao ativar cargo. Status:', response.status);
                            Swal.fire('Erro', 'Erro ao ativar cargo.', 'error');
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
