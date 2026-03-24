<div align="center">

[![Instituto Infnet](https://img.shields.io/badge/Instituto-Infnet-red?style=for-the-badge)](https://www.infnet.edu.br)
[![Curso](https://img.shields.io/badge/Curso-Engenharia_de_Software-blue?style=for-the-badge)](https://www.infnet.edu.br)
[![Disciplina](https://img.shields.io/badge/Disciplina-Refatoração_(DR4)-green?style=for-the-badge)](https://www.infnet.edu.br)

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](https://openjdk.org)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue?logo=apachemaven)](https://maven.apache.org)
[![JUnit](https://img.shields.io/badge/JUnit-5.10-green?logo=junit5&logoColor=white)](https://junit.org/junit5)
[![JaCoCo](https://img.shields.io/badge/Coverage-JaCoCo-red?logo=codecov&logoColor=white)](https://www.jacoco.org)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg?logo=readme&logoColor=white)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Completo-success)](https://github.com/andrebecker84/refactoringDR4_AT)

</div>

# Assessment Final — Refatoração DR4

## Consultoria Técnica em Sistemas Legados

> **Diagnóstico de bad smells, aplicação de técnicas de refatoração e justificativa técnica baseada em Fowler e Clean Code — consolidação das competências da disciplina DR4.**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-@becker84-0077B5?logo=linkedin)](https://linkedin.com/in/becker84)
[![GitHub](https://img.shields.io/badge/GitHub-@andrebecker84-181717?logo=github&logoColor=white)](https://github.com/andrebecker84)

---

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Bad Smells por Questão](#bad-smells-por-questão)
- [Estrutura de Pacotes](#estrutura-de-pacotes)
- [Como Executar](#como-executar)
- [Testes](#testes)
- [Decisões Técnicas por Questão](#decisões-técnicas-por-questão)
- [Referências](#referências)

---

## Sobre o Projeto

Assessment final da disciplina de Refatoração. Seis questões cobrindo diagnóstico de bad smells, refatoração para legibilidade, encapsulamento real, eliminação de condicionais por polimorfismo e substituição de type codes por hierarquias.

O código legado de cada questão está preservado intacto em `original/`. As versões refatoradas aplicam as técnicas do catálogo de Fowler sem alterar o comportamento externo dos sistemas — verificado por testes automatizados na Q1 e pela estrutura da hierarquia na Q6.

---

## Bad Smells por Questão

| Questão  | Bad Smells identificados                                                                                                         | Técnica principal                                                                 |
|----------|----------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| Q1       | Nomes obscuros, Dead Code, Condicional duplicada, Múltiplas responsabilidades                                                    | Rename Method/Variable, Extract Method, Remove Dead Code                          |
| Q2       | Primitive Obsession, Duplicate Code, Dead Code, Bug lógico, Long Method, Feature Envy, Speculative Generality, Encapsulate Field | Extract Method, Replace Primitive with Object (enum), Encapsulate Field           |
| Q3       | Magic Numbers, Parâmetro sem semântica, Lógica condicional opaca                                                                 | Introduce Explaining Variable, Extract Method, Replace Magic Number with Constant |
| Q4       | Campos públicos, List raw sem tipo, Acesso direto à coleção                                                                      | Encapsulate Field, Replace Data Value with Object, Hide Collection                |
| Q5       | Switch excessivo sobre String, OCP violado                                                                                       | Replace Conditional with Polymorphism, Extract Interface                          |
| Q6       | Type Code como String, OCP violado, Baixa coesão, Lógica condicional espalhável                                                  | Replace Type Code with Subclasses, Extract Superclass                             |

---

## Estrutura de Pacotes

```
src/
├── main/java/com/andrebecker/assessment/
│   ├── original/
│   │   ├── Q1_X.java                     # código legado Q1 — preservado intacto
│   │   ├── Q2_Invoice.java               # código legado Q2 — preservado intacto
│   │   ├── Q3_PriceCalculator.java       # código legado Q3 — preservado intacto
│   │   ├── Q4_User.java                  # código legado Q4 — preservado intacto
│   │   ├── Q5_NotificationService.java   # código legado Q5 — preservado intacto
│   │   └── Q6_Document.java              # código legado Q6 — preservado intacto
│   ├── q1/
│   │   └── ClassificationService.java    # refatorado: nomes semânticos, SRP, MÉDIO adicionado
│   ├── q2/
│   │   ├── InvoiceType.java              # enum substitui int type (Primitive Obsession)
│   │   └── Invoice.java                  # encapsulamento, Extract Method, bug corrigido
│   ├── q3/
│   │   └── PriceCalculator.java          # variáveis explicativas, enum CustomerType
│   ├── q4/
│   │   ├── Address.java                  # Value Object imutável
│   │   └── User.java                     # encapsulamento, List<Address>, addAddress()
│   ├── q5/
│   │   ├── NotificationChannel.java      # interface — contrato do canal
│   │   ├── EmailChannel.java             # implementação EMAIL
│   │   ├── SmsChannel.java               # implementação SMS
│   │   ├── PushChannel.java              # implementação PUSH
│   │   └── NotificationService.java      # delega sem condicional
│   └── q6/
│       ├── Document.java                 # classe abstrata com print() abstrato
│       ├── PdfDocument.java              # subclasse PDF
│       ├── HtmlDocument.java             # subclasse HTML
│       ├── WordDocument.java             # subclasse Word (terceiro tipo)
│       └── DocumentApp.java              # main demonstra polimorfismo
└── test/java/com/andrebecker/assessment/
    ├── q1/
    │   └── ClassificationServiceTest.java  # 5 testes: ALTO, MÉDIO, BAIXO, -9999, stdout
    ├── q2/
    │   └── InvoiceTest.java                # 8 testes: criação válida, validações, process(), enum
    ├── q3/
    │   └── PriceCalculatorTest.java        # 6 testes: CustomerType × holiday
    ├── q4/
    │   └── UserTest.java                   # 15 testes: User + Address, validações, imutabilidade
    ├── q5/
    │   └── NotificationServiceTest.java    # 4 testes: cada canal + canal nulo
    └── q6/
        └── DocumentTest.java               # 5 testes: subclasses, polimorfismo, DocumentApp.main()
```

---

## Como Executar

**Pré-requisitos:** JDK 21+, Maven 3.9+

```bash
# Build completo + cobertura JaCoCo
mvn clean verify

# Apenas testes
mvn test

# Compilar sem testes
mvn clean compile
```

**Scripts interativos:**

```bash
# Windows
run.bat

# Linux / macOS
chmod +x run.sh && ./run.sh
```

Relatório JaCoCo gerado em `target/site/jacoco/index.html`.

**Evidências de execução** disponíveis em `doc/screenshots/`:

| Screenshot        | Conteúdo                          |
|-------------------|-----------------------------------|
| `mvn_verify.png`  | Build completo + resumo JaCoCo    |
| `documentapp.png` | Saída do `DocumentApp` (Q6)       |
| `jacoco.png`      | Relatório de cobertura por pacote |

---

## Testes

**43 testes — 0 falhas — `mvn test`**

| Questão  | Testes  | Comportamentos validados                                                                          |
|----------|---------|---------------------------------------------------------------------------------------------------|
| Q1       | 5       | ALTO / MÉDIO / BAIXO / CASO RARO / saída stdout de `printClassification`                          |
| Q2       | 8       | Criação válida, rejeição de nome·e-mail·valor·tipo inválidos, `process()`, labels do enum         |
| Q3       | 6       | Todas combinações `CustomerType` × `isHoliday` (STANDARD, PREMIUM, GUEST)                         |
| Q4       | 15      | `User`: campos, validações, `addAddress`, imutabilidade; `Address`: campos e validações Fail-Fast |
| Q5       | 4       | `EmailChannel`, `SmsChannel`, `PushChannel` e rejeição de canal nulo                              |
| Q6       | 5       | `PdfDocument`, `HtmlDocument`, `WordDocument`, dispatch polimórfico, `DocumentApp.main()`         |

---

## Decisões Técnicas por Questão

**Q1 — Rename + Extract + Remove Dead Code**  
- `X`, `y`, `z` são nomes que não comunicam nada. `ClassificationService`, `printClassification`, `value` descrevem exatamente o que cada elemento faz. 
- O dead code (`temp = z * 0 + 42`, `System.out.println("DEBUG: z = " + z)`, o segundo `if (z > 10 && z > 5)`) foi removido — código que nunca é executado ou que imprime sem propósito não tem lugar no sistema refatorado.
- O caso `-9999` foi mantido por decisão de negócio e ganhou a constante `SPECIAL_CASE` para tornar a intenção explícita.

**Q2 — Primitive Obsession → enum + Duplicate Code → Extract Method**  
- O campo `int type` aceitava qualquer inteiro, incluindo `-1` ("nunca ocorre"). 
- O enum `InvoiceType` torna os valores inválidos impossíveis em tempo de compilação. 
- O bloco `if (type == 1/2)` duplicado foi eliminado por `formatNote()`, que é a única fonte da representação textual da nota — tanto para impressão quanto para envio por e-mail. 
- O bug `clientEmail == null && !clientEmail.contains("@")` (que causaria NPE) foi corrigido para `||` e encapsulado em `isEmailValid()`.

**Q3 — Introduce Explaining Variable + enum com taxa embutida**  
- O parâmetro `int customerType` era um Magic Number. 
- `CustomerType.STANDARD` e `CustomerType.PREMIUM` comunicam intenção sem precisar de comentário. 
- A taxa de desconto foi embutida no enum — o valor e seu significado estão no mesmo lugar. 
- As variáveis `baseDiscount`, `holidayDiscount` e `totalDiscount` tornam o cálculo auditável linha a linha.

**Q4 — Encapsulate Field + Replace Data Value with Object + Hide Collection**  
- `List` raw sem tipo genérico aceita qualquer objeto em runtime — erro detectável apenas em produção. 
- `List<Address>` garante consistência em tempo de compilação. 
- `Address` é imutável por design: sem setters, validação no construtor. 
- `getAddresses()` retorna `Collections.unmodifiableList()` — o chamador não pode modificar a lista interna sem passar por `addAddress()`.

**Q5 — Replace Conditional with Polymorphism**  
- A condição `if (channel.equals("EMAIL"))` é um Switch excessivo disfarçado de if/else. 
- Cada novo canal exigia modificar `NotificationService`. 
- A interface `NotificationChannel` inverte esse fluxo: `NotificationService` não sabe qual canal está usando — apenas delega. 
- Adicionar `WhatsAppChannel` não toca em nenhuma classe existente.

**Q6 — Replace Type Code with Subclasses**  
- O campo `String type` não impedia instâncias com `type = "WORD"` de entrar em `print()` e cair no `else` silencioso.
- A hierarquia abstrata elimina esse risco: só existem instâncias de subclasses concretas.
- O `DocumentApp.main()` demonstra que três chamadas a `print()` produzem três comportamentos distintos sem um único `if`.

---

## Referências

- MARTIN, Robert C. *Clean Code: A Handbook of Agile Software Craftsmanship*. 2. ed. Prentice Hall, 2008.
- FOWLER, Martin. *Refactoring: Improving the Design of Existing Code*. 2. ed. Addison-Wesley, 2018.
- BECK, Kent. *Test Driven Development: By Example*. Addison-Wesley, 2002.
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)

---

<div align="center">

<p><strong>Desenvolvido como Assessment Final da disciplina de Engenharia de Software com foco em Refatoração.</strong></p>

<p>
  <a href="https://www.java.com/"><img src="https://img.shields.io/badge/Made%20with-Java-orange?logo=openjdk" alt="Java"></a>
  <a href="https://maven.apache.org/"><img src="https://img.shields.io/badge/Built%20with-Maven-blue?logo=apachemaven" alt="Maven"></a>
  <a href="https://junit.org/junit5/"><img src="https://img.shields.io/badge/Tested%20with-JUnit-green?logo=junit5&logoColor=white" alt="JUnit"></a>
</p>

<a href="doc/images/card.svg">
  <img src="doc/images/card.svg" width="360" alt="André Luis Becker - Software Engineer">
</a>

<p><em>Instituto Infnet — Engenharia de Software — 2026.</em></p>

<p>
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-MIT-yellow.svg?logo=readme&logoColor=white" alt="MIT License"></a>
</p>

</div>
