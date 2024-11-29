document.addEventListener("DOMContentLoaded", function() {
    var cargoDropzone = new Dropzone("#CargoDropzone", {
        autoProcessQueue: false,
        maxFilesize: 10,
        maxFiles: null,
        init: function () {
            this.on("addedfile", function (file) {
                // Código para quando um arquivo é adicionado
            });
            this.on("removedfile", function (file) {
                // Código para quando um arquivo é removido
            });
            this.on("complete", function (file) {
                if (!file.accepted) {
                    this.removeFile(file);
                    Swal.fire("Erro", "Arquivo rejeitado pelos padrões do sistema!", "error");
                }
            });
        }
    });
});
