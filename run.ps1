#Requires -Version 7
# run.ps1 — Menu interativo multiplataforma: build, testes e cobertura

Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'

$CYAN  = "`e[36m"; $GREEN = "`e[32m"; $RED = "`e[31m"; $RESET = "`e[0m"
$ROOT  = $PSScriptRoot

foreach ($tool in @('java', 'mvn')) {
    if (-not (Get-Command $tool -ErrorAction SilentlyContinue)) {
        Write-Host "${RED}[ERRO]${RESET} '$tool' nao encontrado. Verifique o PATH."
        exit 1
    }
}

function Open-JaCoCoReport {
    $report = Join-Path $ROOT "target\site\jacoco\index.html"
    if (-not (Test-Path $report)) {
        Write-Host "${RED}Relatorio nao encontrado.${RESET} Execute a opcao [3] primeiro."
        return
    }
    Start-Process $report
    Write-Host "Relatorio aberto no navegador."
}

while ($true) {
    Clear-Host
    Write-Host "${CYAN}============================================================${RESET}"
    Write-Host "${CYAN}  refactoringDR4_AT -- Assessment Final de Refatoracao${RESET}"
    Write-Host "${CYAN}============================================================${RESET}"
    Write-Host ""
    Write-Host "  [1] Compilar"
    Write-Host "  [2] Executar testes"
    Write-Host "  [3] Build completo + JaCoCo"
    Write-Host "  [4] Abrir relatorio JaCoCo"
    Write-Host "  [5] Executar DocumentApp (Q6)"
    Write-Host "  [6] Limpar build"
    Write-Host "  [0] Sair"
    Write-Host ""
    $opcao = Read-Host "Escolha"

    switch ($opcao) {
        '1' {
            Write-Host ""
            Write-Host "${CYAN}>> mvn clean compile${RESET}"
            Write-Host ""
            mvn clean compile
            Read-Host "Pressione Enter..."
        }
        '2' {
            Write-Host ""
            Write-Host "${CYAN}>> mvn test  [Q1-Q6 — JUnit 5]${RESET}"
            Write-Host ""
            mvn test
            Read-Host "Pressione Enter..."
        }
        '3' {
            Write-Host ""
            Write-Host "${CYAN}>> mvn clean verify  [build completo + JaCoCo]${RESET}"
            Write-Host ""
            mvn clean verify
            Read-Host "Pressione Enter..."
        }
        '4' { Open-JaCoCoReport;  Read-Host "Pressione Enter..." }
        '5' {
            Write-Host ""
            Write-Host "${CYAN}>> mvn compile  +  DocumentApp (Q6)${RESET}"
            Write-Host ""
            mvn compile
            if ($LASTEXITCODE -ne 0) {
                Write-Host "${RED}[ERRO] Compilacao falhou.${RESET}"
                Read-Host "Pressione Enter..."
                continue
            }
            Write-Host ""
            Write-Host "${CYAN}>> java -cp target\classes com.andrebecker.assessment.q6.DocumentApp${RESET}"
            Write-Host ""
            java -cp "target\classes" com.andrebecker.assessment.q6.DocumentApp
            Read-Host "Pressione Enter..."
        }
        '6' {
            Write-Host ""
            Write-Host "${CYAN}>> mvn clean${RESET}"
            Write-Host ""
            mvn clean
            Write-Host "${GREEN}Build removido.${RESET}"
            Read-Host "Pressione Enter..."
        }
        '0' { exit 0 }
        default { Write-Host "Opcao invalida." }
    }
}
