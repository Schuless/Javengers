document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        // Confirmação antes de excluir
        if (confirm('Confirma a exclusão?')) {
            // Recupera a linha <tr> que contém o botão de exclusão
            const row = this.closest('tr');

            // Recupera o código do cargo do atributo 'data-cargo-codigo'
            const cargoCodigo = this.dataset.cargoCodigo;

            // Verifica se o código do cargo foi recuperado corretamente
            if (!cargoCodigo) {
                console.error("Código do cargo não encontrado.");
                alert("Erro: código do cargo não encontrado.");
                return;
            }

            // Realiza a requisição para excluir o cargo
            fetch(`/cargos/${cargoCodigo}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Cargo excluído com sucesso.');
                        row.remove();  // Remove a linha da tabela após a exclusão bem-sucedida
                    } else {
                        console.error('Erro ao excluir cargo. Status:', response.status);
                        alert('Erro ao excluir cargo');
                    }
                })
                .catch(error => {
                    console.error('Erro de rede:', error);
                    alert('Erro de rede: ' + error);
                });
        }
    });
});
