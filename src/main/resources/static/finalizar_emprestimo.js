document.querySelectorAll('.finalizar').forEach(function(button) {
    button.addEventListener('click', function() {
        // Confirmação antes de finalizar
        if (confirm('Confirma a finalização do empréstimo?')) {
            // Recupera a linha <tr> que contém o botão de finalizar
            const row = this.closest('tr');

            // Recupera o código do empréstimo do atributo 'data-emprestimo-codigo'
            const emprestimoCodigo = this.dataset.emprestimoCodigo;

            // Verifica se o código do empréstimo foi recuperado corretamente
            if (!emprestimoCodigo) {
                console.error("Código do empréstimo não encontrado.");
                alert("Erro: código do empréstimo não encontrado.");
                return;
            }

            // Realiza a requisição para finalizar o empréstimo
            fetch(`/emprestimos/finalizar/${emprestimoCodigo}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Empréstimo finalizado com sucesso.');
                        row.remove();  // Remove a linha da tabela após a finalização bem-sucedida
                    } else {
                        console.error('Erro ao finalizar empréstimo. Status:', response.status);
                        alert('Erro ao finalizar empréstimo');
                    }
                })
                .catch(error => {
                    console.error('Erro de rede:', error);
                    alert('Erro de rede: ' + error);
                });
        }
    });
});
