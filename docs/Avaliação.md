Cada aluno deverá desenvolver **individualmente** um **aplicativo Android** com uma proposta coerente, cujas funcionalidades estejam relacionadas a um mesmo problema ou tema.

## Parcial — Android Views (XML) e navegação com Intent

### Requisitos obrigatórios
O aplicativo deve:
- Implementar duas telas em Android Views com layouts XML, usando Views (i.e. `TextView`, `ImageView`, `Space`) e ViewGroups (i.e. `LinearLayout`, `FrameLayout`, `RecyclerView`) adequados ao fluxo do aplicativo.
- Implementar a navegação entre as duas telas por meio de `Intent` explícita, com passagem de dados quando aplicável.
- Conectar as Views ao Kotlin por `ViewBinding` ou `findViewById`, tratando pelo menos uma interação que atualize a interface ou avance o fluxo.
- Representar os dados exibidos com modelos imutáveis (`data class`) e tratar corretamente valores opcionais.
- Utilizar dados simulados (*mocks*). Nesta etapa, o uso de API ou banco de dados é opcional.

### Itens opcionais
- Usar `ViewBinding` em todas as telas.
- Criar componentes XML reutilizáveis, inflá-los quando necessário e tratar eventos que atualizem a interface.
- Implementar uma interface funcional em um `Fragment`, utilizando seu ciclo de vida e `ViewBinding`.

## Final — Jetpack Compose

O trabalho final vale **7 pontos**. Abaixo, estão os itens (e suas respectivas pontuações) que serão utilizados para compor a nota do projeto.

### Itens avaliativos
- [ ] **1 ponto** — Implementar pelo menos três telas conectadas entre si, com toda a interface em Jetpack Compose, sem layouts XML.
- [ ] **0,5 ponto** — Exibir pelo menos uma lista utilizando `LazyRow` ou `LazyColumn`.
- [ ] **1 ponto** — Usar `ViewModel` para gerenciar o estado da interface e responder às ações do usuário.
- [ ] **1 ponto** — Executar pelo menos uma operação assíncrona relevante para o aplicativo usando coroutines.
- [ ] **1 ponto** — Apresentar _feedback_ adequado a cada um dos possíveis estados da operação (ou das operações) assíncronas do app (ex. loading, error, success, lista vazia).
- [ ] **1 ponto** — Implementar a navegação com Navigation 3 em uma única `Activity`.
- [ ] **0,5 ponto** — Tratar corretamente valores opcionais, como `String?` e `Int?`.
- [ ] **1 ponto** — Serializar ou desserializar dados usando `kotlinx.serialization`.
- [ ] **Obrigatório** — Manter dados sensíveis (como chaves de API e senhas de banco de dados) fora dos arquivos versionados e do histórico do repositório do GitHub. O descumprimento deste item resulta em **zero** na nota do trabalho.
- [ ] **Obrigatório** — Manter dados sensíveis fora dos logs gerados pelo aplicativo, inclusive no Logcat. O descumprimento deste item resulta em **zero** na nota do trabalho.

## Condições de entrega e execução
- Entregue o link do repositório no GitHub utilizando a atividade correspondente no Google Classroom.
- Se o projeto exigir chaves de API, senhas ou outras credenciais, guarde-as em um arquivo de configuração local, como `secrets.properties`. Envie esse arquivo separadamente ao professor, adicione-o ao `.gitignore` e mantenha-o fora do repositório.
- O `README.md` deve incluir uma breve descrição do objetivo do aplicativo, os pré-requisitos e os passos para compilar e executar o projeto localmente.
- Recomenda-se entregar o trabalho com pelo menos uma aula antes do prazo final, para que eventuais problemas de execução possam ser verificados em aula.
- O projeto deve compilar e executar sem alterações no código-fonte. Se o professor seguir as instruções do `README.md`, incluindo a configuração local quando aplicável, e o projeto falhar na compilação ou na execução, a nota do trabalho será **zero**.
- O estudante deverá ser capaz de explicar as decisões técnicas, a arquitetura e o funcionamento do código entregue.