document.querySelectorAll('.desativar').forEach(function(button) {
    button.addEventListener('click', function() {
        // Confirmação antes de desativar
        if (confirm('Confirma a desativação do cargo?')) {
            // Recupera a linha <tr> que contém o botão de desativar
            const row = this.closest('tr');

            // Recupera o código do cargo do atributo 'data-cargo-codigo'
            const cargoCodigo = this.dataset.cargoCodigo;

            // Verifica se o código do cargo foi recuperado corretamente
            if (!cargoCodigo) {
                console.error("Código do cargo não encontrado.");
                alert("Erro: código do cargo não encontrado.");
                return;
            }

            // Realiza a requisição para desativar o cargo
            fetch(`/cargos/desativar/${cargoCodigo}`, {
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
