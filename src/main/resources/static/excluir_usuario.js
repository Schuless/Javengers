document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        // Confirmação antes de excluir
        if (confirm('Confirma a exclusão?')) {
            // Recupera a linha <tr> que contém o botão de exclusão
            const row = this.closest('tr');

            // Recupera o código do usuário do atributo 'data-usuario-codigo'
            const usuarioCodigo = this.dataset.usuarioCodigo;

            // Verifica se o código do usuário foi recuperado corretamente
            if (!usuarioCodigo) {
                console.error("Código do usuário não encontrado.");
                alert("Erro: código do usuário não encontrado.");
                return;
            }

            // Realiza a requisição para excluir o usuário
            fetch(`/usuarios/${usuarioCodigo}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Usuário excluído com sucesso.');
                        row.remove();  // Remove a linha da tabela após a exclusão bem-sucedida
                    } else {
                        console.error('Erro ao excluir usuário. Status:', response.status);
                        alert('Erro ao excluir usuário');
                    }
                })
                .catch(error => {
                    console.error('Erro de rede:', error);
                    alert('Erro de rede: ' + error);
                });
        }
    });
});
