/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

$(document).ready(function() {
    // Extrai o ID do filme da URL /filmes/{id}
    const pathParts = window.location.pathname.split('/');
    const filmeId = pathParts[pathParts.length - 1];

    if (!filmeId || isNaN(filmeId)) {
        alert("Filme não encontrado!");
        return;
    }

    // Carrega informações do filme
    $.get(`/api/filmes/${filmeId}`, function(filme) {
        $("#titulo-filme").text(`${filme.titulo} (${filme.anoLancamento})`);
        $("#genero").text(filme.genero);
        $("#sinopse").text(filme.sinopse);
    }).fail(() => {
        alert("Erro ao carregar informações do filme.");
    });

    // Carrega análises do filme
    function carregarAnalises() {
        $.get(`/api/analises/filme/${filmeId}`, function(analises) {
            const container = $("#analises-container");
            container.empty();

            if (analises.length === 0) {
                container.append("<p>Nenhuma análise cadastrada.</p>");
            } else {
                analises.forEach(a => {
                    container.append(`
                        <div class="analise-card" data-id="${a.id}">
                            <div class="analise-content">
                                <p class="analise-texto">${a.texto}</p>
                                <p class="analise-nota"><strong>Nota:</strong> ${a.nota}</p>
                            </div>
                            <div class="analise-actions">
                                <button class="editar" data-id="${a.id}" title="Editar análise">✏️</button>
                                <button class="excluir" data-id="${a.id}" title="Excluir análise">❌</button>
                            </div>
                        </div>
                    `);
                });
            }
        }).fail(() => {
            alert("Erro ao carregar análises.");
        });
    }

    carregarAnalises();
    
    // Envia nova análise
    $("#form-analise").submit(function(e) {
        e.preventDefault();

        const analise = {
            texto: $("#texto").val(),
            nota: parseFloat($("#nota").val())
        };

        $.ajax({
            url: `/api/analises/filme/${filmeId}`,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(analise),
            success: function() {
                $("#form-analise")[0].reset();
                carregarAnalises();
            },
            error: function() {
                alert("Erro ao adicionar análise.");
            }
        });
    });
    
    // Evento para cancelar e à lista de filmes
    $("#cancelar").on("click", function() {
        window.location.href = "/filmes";
    });
    
    // Evento para excluir análise
    $(document).on("click", ".excluir", function() {
        const analiseId = $(this).data("id");
        if (confirm("Tem certeza que deseja excluir esta análise?")) {
            $.ajax({
                url: `/api/analises/${analiseId}`,
                type: "DELETE",
                success: function() {
                    carregarAnalises(); // Recarrega as análises
                },
                error: function() {
                    alert("Erro ao excluir análise.");
                }
            });
        }
    });
    
    // Evento para editar uma análise
    $(document).on("click", ".editar", function() {
        const analiseId = $(this).data("id");
        window.location.href = "/analises/edit/" + analiseId;
    });
    
});

