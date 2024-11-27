document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        // Confirmação antes de excluir
        if (confirm('Confirma a exclusão?')) {
            // Recupera a linha <tr> que contém o botão de exclusão
            const row = this.closest('tr');

            // Recupera o código do colaborador do atributo 'data-colaborador-codigo'
            const colaboradorCodigo = this.dataset.colaboradorCodigo;

            // Verifica se o código do colaborador foi recuperado corretamente
            if (!colaboradorCodigo) {
                console.error("Código do colaborador não encontrado.");
                alert("Erro: código do colaborador não encontrado.");
                return;
            }

            // Realiza a requisição para excluir o colaborador
            fetch(`/colaboradores/${colaboradorCodigo}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Colaborador excluído com sucesso.');
                        row.remove();  // Remove a linha da tabela após a exclusão bem-sucedida
                    } else {
                        console.error('Erro ao excluir colaborador. Status:', response.status);
                        alert('Erro ao excluir colaborador');
                    }
                })
                .catch(error => {
                    console.error('Erro de rede:', error);
                    alert('Erro de rede: ' + error);
                });
        }
    });
});
