document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        // Confirmação antes de excluir
        if (confirm('Confirma a exclusão do empréstimo?')) {
            // Recupera a linha <tr> que contém o botão de exclusão
            const row = this.closest('tr');

            // Recupera o código do empréstimo do atributo 'data-emprestimo-codigo'
            const emprestimoCodigo = this.dataset.emprestimoCodigo;

            // Verifica se o código do empréstimo foi recuperado corretamente
            if (!emprestimoCodigo) {
                console.error("Código do empréstimo não encontrado.");
                alert("Erro: código do empréstimo não encontrado.");
                return;
            }

            // Realiza a requisição para excluir o empréstimo
            fetch(`/emprestimos/${emprestimoCodigo}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Empréstimo excluído com sucesso.');
                        row.remove();  // Remove a linha da tabela após a exclusão bem-sucedida
                    } else {
                        console.error('Erro ao excluir empréstimo. Status:', response.status);
                        alert('Erro ao excluir empréstimo');
                    }
                })
                .catch(error => {
                    console.error('Erro de rede:', error);
                    alert('Erro de rede: ' + error);
                });
        }
    });
});
