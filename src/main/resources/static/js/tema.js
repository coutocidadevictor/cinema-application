/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

$(document).ready(function () {
    const botaoTema = $("#alternar-tema");
    const corpo = $("body");

    // Funções auxiliares
    function salvarTemaCookie(tema) {
        // Cookie expira em 30 dias
        const data = new Date();
        data.setTime(data.getTime() + (30 * 24 * 60 * 60 * 1000));
        document.cookie = "tema=" + tema + "; expires=" + data.toUTCString() + "; path=/";
    }

    function lerTemaCookie() {
        const cookies = document.cookie.split("; ");
        for (const c of cookies) {
            const [chave, valor] = c.split("=");
            if (chave === "tema") return valor;
        }
        return null;
    }

    function aplicarTema(tema) {
        if (tema === "escuro") {
            corpo.addClass("dark-mode");
            botaoTema.text("☀️ Modo claro");
        } else {
            corpo.removeClass("dark-mode");
            botaoTema.text("🌙 Modo escuro");
        }
    }

    // Inicializa o tema a partir do cookie
    const temaSalvo = lerTemaCookie();
    if (temaSalvo) {
        aplicarTema(temaSalvo);
    } else {
        aplicarTema("claro"); // padrão
    }

    // Ajuste ao clicar no botão
    botaoTema.on("click", function () {
        const temaAtual = corpo.hasClass("dark-mode") ? "escuro" : "claro";
        const novoTema = temaAtual === "escuro" ? "claro" : "escuro";
        aplicarTema(novoTema);
        salvarTemaCookie(novoTema);
    });
});

