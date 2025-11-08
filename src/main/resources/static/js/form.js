/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

$(document).ready(function() {
    // Extrai o ID do filme da URL /filmes/edit/{id}
    const pathParts = window.location.pathname.split('/');
    const filmeId = pathParts[pathParts.length - 1];

    // Modo edição
    if (filmeId && !isNaN(filmeId)) {
        $("#titulo-form").text("Editar Filme"); // Altera o título da página
        $("#filmeId").val(filmeId);

        // Busca os dados do filme e preenche o formulário
        $.ajax({
            url: "/api/filmes/" + filmeId,
            type: "GET",
            success: function(filme) {
                $('#titulo').val(filme.titulo);
                $('#sinopse').val(filme.sinopse);
                $('#genero').val(filme.genero);
                $('#anoLancamento').val(filme.anoLancamento);
            },
            error: function(xhr) {
                console.error(xhr);
                alert('Não foi possível carregar os dados do filme.');
            }
        });

    // Modo cadastro
    } else {
        $("#titulo-form").text("Cadastrar Novo Filme");
    }
    
    // --- Submissão do formulário ---
    $('#formFilme').on('submit', function(event) {
        event.preventDefault();
        
        const filmeId = $('#filmeId').val();
        
        const filme = {
            titulo: $('#titulo').val(),
            sinopse: $('#sinopse').val(),
            genero: $('#genero').val(),
            anoLancamento: parseInt($('#anoLancamento').val())
        };
        
        // Verifica se o id do filme foi atribuído, indicando edição (PUT)
        const metodo = filmeId ? 'PUT' : 'POST';
        
        // Define a URL correta em cada caso
        const url = filmeId ? "/api/filmes/" + filmeId : '/api/filmes';

        $.ajax({
            url: url,
            type: metodo,
            contentType: 'application/json',
            data: JSON.stringify(filme),
            success: function() {
                window.location.href = '/filmes';
            },
            error: function(xhr) {
                console.error(xhr);
                alert('Erro ao salvar filme.');
            }
        });
    });
    
    // Evento para cancelar e à lista de filmes
    $("#cancelar").on("click", function() {
        window.location.href = "/filmes";
    });
    
});



