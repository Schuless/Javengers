document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        // Confirmação antes de excluir
        if (confirm('Confirma a exclusão do EPI?')) {
            // Recupera a linha <tr> que contém o botão de exclusão
            const row = this.closest('tr');

            // Recupera o código do EPI do atributo 'data-epi-codigo'
            const epiCodigo = this.dataset.epiCodigo;

            // Verifica se o código do EPI foi recuperado corretamente
            if (!epiCodigo) {
                console.error("Código do EPI não encontrado.");
                alert("Erro: código do EPI não encontrado.");
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
                    } else {
                        console.error('Erro ao excluir EPI. Status:', response.status);
                        alert('Erro ao excluir EPI');
                    }
                })
                .catch(error => {
                    console.error('Erro de rede:', error);
                    alert('Erro de rede: ' + error);
                });
        }
    });
});
