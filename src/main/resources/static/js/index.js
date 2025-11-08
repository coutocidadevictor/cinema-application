$(document).ready(function() {
    // Confirmação de carregamento para debug
    console.log("jQuery e filmes.js carregados");

    // Função para carregar filmes da API REST
    function carregarFilmes() {
        $.get("/api/filmes", function(filmes) {
            console.log("Filmes recebidos:", filmes);
            $("#tabela-filmes").empty(); // limpa tabela antes de preencher
            
            // Popula tabela
            filmes.forEach(function(filme) {
                $("#tabela-filmes").append(`
                    <tr>
                        <td>${filme.titulo}</td>
                        <td>${filme.genero}</td>
                        <td>${filme.anoLancamento}</td>
                        <td>
                            <button class="analise" data-id="${filme.id}">📄 ️Análises</button>
                            <button class="editar" data-id="${filme.id}">✏️ Editar</button>
                            <button class="excluir" data-id="${filme.id}">️🗑️ Excluir</button>
                        </td>
                    </tr>
                `);
            }); 
        }).fail(function() {
            alert("Erro ao carregar filmes da API.");
        });
    }

    // Carrega filmes ao abrir a página
    carregarFilmes();

    // Evento para deletar um filme
    $(document).on("click", ".excluir", function() {
        const id = $(this).data("id");
        if (confirm("Tem certeza que deseja excluir este filme?")) {
            $.ajax({
                url: `/api/filmes/${id}`,
                type: "DELETE",
                success: function() {
                    carregarFilmes(); // Recarrega filmes na tabela
                },
                error: function() {
                    alert("Erro ao excluir filme.");
                }
            });
        }
    });
    
    
    // Evento para editar um filme
    $(document).on("click", ".editar", function() {
        const id = $(this).data("id");
        window.location.href = "/filmes/edit/" + id;
    });
    
    // Evento para abrir página de detalhes e análises de um filme
    $(document).on("click", ".analise", function() {
        const id = $(this).data("id");
        // Redireciona o usuário para a página de detalhes do filme
        window.location.href = "/filmes/" + id;
    });    
    
    
    // Evento para abrir o formulário de novo filme
    $("#novo-filme").on("click", function() {
        window.location.href = "/filmes/new";
    });
    
    
});
