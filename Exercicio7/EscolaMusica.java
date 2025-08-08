public class EscolaMusica {
    public static void main(String[] args) {
        //ALUNOS
        Aluno ana = new Aluno("Ana", 18);
        Aluno bruno = new Aluno("Bruno", 20);
        Aluno clara = new Aluno("Clara", 17);
        Aluno daniel = new Aluno("Daniel", 21);

        //CURSOS
        Curso piano = new Curso("Piano Básico", 6);
        Curso violao = new Curso("Violão Avançado", 12);
        Curso canto = new Curso("Canto Coral", 8);

        //MATRÍCULAS
        ana.matricular(piano);
        bruno.matricular(piano);
        bruno.matricular(violao);
        clara.matricular(canto);
        daniel.matricular(violao);
        daniel.matricular(canto);

        //PROFESSORES 
        Professor profJoao = new Professor("João", "Piano");
        profJoao.adicionarCurso(piano);

        Professor profMaria = new Professor("Maria", "Canto e Violão");
        profMaria.adicionarCurso(violao);
        profMaria.adicionarCurso(canto);

        //INSTRUMENTOS
        Instrumento guitarra = new Instrumento("Guitarra", "Cordas");
        guitarra.adicionarAcessorio("Correia", "Correia de couro preta");
        guitarra.adicionarAcessorio("Cabo", "Cabo P10 5m");

        Instrumento teclado = new Instrumento("Teclado", "Teclas");
        teclado.adicionarAcessorio("Fonte", "Fonte 12V");
        teclado.adicionarAcessorio("Suporte", "Suporte dobrável");

        Instrumento microfone = new Instrumento("Microfone", "Voz");
        microfone.adicionarAcessorio("Filtro Pop", "Filtro anti-estouro");
        microfone.adicionarAcessorio("Tripé", "Tripé ajustável");

        //TESTES DE REMOÇÃO
        // Desmatricular aluno
        bruno.desmatricular(piano);

        // Remover curso do professor
        profMaria.removerCurso(canto);

        // Remover acessório
        teclado.removerAcessorio(teclado.getAcessorios().get(0)); 

       
        System.out.println("=========== ALUNOS E SEUS CURSOS ===========");
        for (Aluno aluno : new Aluno[]{ana, bruno, clara, daniel}) {
            System.out.println("Aluno: " + aluno.getNome());
            for (Curso c : aluno.getCursos()) {
                System.out.println(" - " + c.getTitulo());
            }
        }

        System.out.println("\n=========== CURSOS E PROFESSORES ===========");
        for (Curso c : new Curso[]{piano, violao, canto}) {
            System.out.print("Curso: " + c.getTitulo());
            if (c.getProfessor() != null) {
                System.out.println(" | Professor: " + c.getProfessor().getNome());
            } else {
                System.out.println(" | Professor: (sem professor para canto coral por enquanto!!");
            }
        }

        System.out.println("\n=========== INSTRUMENTOS E ACESSÓRIOS ===========");
        for (Instrumento inst : new Instrumento[]{guitarra, teclado, microfone}) {
            System.out.println("Instrumento: " + inst.getNome() + " (" + inst.getTipo() + ")");
            for (Instrumento.Acessorio acc : inst.getAcessorios()) {
                System.out.println(" - " + acc.getNome() + ": " + acc.getDescricao());
            }
            System.out.println();
        }
    }
}
