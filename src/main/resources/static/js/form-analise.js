/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

$(document).ready(function() {
    // Extrai o ID da análise da URL /analises/edit/{id}
    const pathParts = window.location.pathname.split('/');
    const analiseId = pathParts[pathParts.length - 1];
    
    let filmeId = null;

    // Se encontrar a análise
    if (analiseId && !isNaN(analiseId)) {
        // Busca os dados da análise e preenche o formulário
        $.ajax({
            url: "/api/analises/" + analiseId,
            type: "GET",
            success: function(analise) {
                $('#texto').val(analise.texto);
                $('#nota').val(analise.nota);
                $('#analiseId').val(analise.id);
                
                // Armazena o ID do filme para usar ao voltar
                if (analise.filme && analise.filme.id) {
                    filmeId = analise.filme.id;
                }
            },
            error: function(xhr) {
                console.error(xhr);
                alert('Não foi possível carregar os dados da análise.');
            }
        });
    }
    
    // Submissão do formulário
    $('#formAnalise').on('submit', function(event) {
        event.preventDefault();
        
        const analiseId = $('#analiseId').val();
        
        const analise = {
            texto: $('#texto').val(),
            nota: parseFloat($('#nota').val())
        };
        
        $.ajax({
            url: "/api/analises/" + analiseId,
            type: "PUT",
            contentType: 'application/json',
            data: JSON.stringify(analise),
            success: function(response) {
                // Redireciona de volta para a página do filme
                if (filmeId) {
                    window.location.href = '/filmes/' + filmeId;
                } else {
                    // Volta para a página anterior
                    window.history.back();
                }
            },
            error: function(xhr) {
                console.error(xhr);
                alert('Erro ao salvar análise.');
            }
        });
    });
    
    // Evento para cancelar
    $("#cancelar").on("click", function() {
        if (filmeId) {
            window.location.href = '/filmes/' + filmeId;
        } else {
            window.history.back();
        }
    });
    
});