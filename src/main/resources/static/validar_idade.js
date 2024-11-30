// Função para calcular a idade com base na data de nascimento
function calcularIdade(dataNascimento) {
    const hoje = new Date();
    const nascimento = new Date(dataNascimento);
    let idade = hoje.getFullYear() - nascimento.getFullYear();
    const mes = hoje.getMonth() - nascimento.getMonth();

    // Se a data de nascimento ainda não ocorreu este ano, subtrai 1 da idade
    if (mes < 0 || (mes === 0 && hoje.getDate() < nascimento.getDate())) {
        idade--;
    }

    return idade;
}

// Função de validação de idade
function validarIdade(event) {
    event.preventDefault(); // Impede o envio do formulário caso a validação falhe

    const dataNascimento = document.getElementById('dataNascimento').value;
    const idade = calcularIdade(dataNascimento);

    // Verifica se a idade está entre 18 e 100 anos
    if (idade >= 18 && idade <= 100) {
        // Se a idade for válida, o formulário pode ser enviado
        Swal.fire({
            title: 'Idade válida!',
            text: 'Cadastro será realizado.',
            icon: 'success',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        }).then(() => {
            document.getElementById('formColaborador').submit(); // Envia o formulário
        });
    } else {
        // Se a idade for inválida, exibe uma mensagem de erro
        Swal.fire({
            title: 'Idade Inválida!',
            text: 'O colaborador deve ter entre 18 e 100 anos.',
            icon: 'error',
            confirmButtonColor: '#d33',
            confirmButtonText: 'Ok'
        });
    }
}

// Adiciona o evento de submit ao formulário para chamar a validação
document.getElementById('formColaborador').addEventListener('submit', validarIdade);
