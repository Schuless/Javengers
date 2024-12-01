const fileInput = document.getElementById('fileInput');
const fileNameDisplay = document.getElementById('fileName');
const uploadContainer = document.querySelector('.upload-container');

fileInput.addEventListener('change', function(event) {
    const file = event.target.files[0]; // Pega o primeiro arquivo selecionado
    if (file) {
        fileNameDisplay.textContent = `Arquivo selecionado: ${file.name}`;
    } else {
        fileNameDisplay.textContent = "Nenhum arquivo selecionado";
    }
});

uploadContainer.addEventListener('dragover', (event) => {
    event.preventDefault();
    uploadContainer.classList.add('drag-over');
});

uploadContainer.addEventListener('dragleave', () => {
    uploadContainer.classList.remove('drag-over');
});

uploadContainer.addEventListener('drop', (event) => {
    event.preventDefault();
    uploadContainer.classList.remove('drag-over');
    const files = event.dataTransfer.files;
    if (files.length > 0) {
        fileInput.files = files; // Atribui os arquivos arrastados ao input
        fileNameDisplay.textContent = `Arquivo selecionado: ${files[0].name}`; // Exibe o nome do arquivo
    }
});
