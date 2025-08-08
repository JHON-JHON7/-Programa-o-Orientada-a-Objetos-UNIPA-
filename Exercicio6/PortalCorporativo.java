public class PortalCorporativo {
    public static void main(String[] args) {
       
        Desenvolvedor dev1 = new Desenvolvedor("Lucas", "85358336680", "DEV001", 4000);
        dev1.setLinguagem1("Java");
        dev1.setLinguagem2("Python");

        Desenvolvedor dev2 = new Desenvolvedor("Marina", "03943059650", "DEV002", 4200);
        dev2.setLinguagem1("PHP");
        dev2.setLinguagem2("JavaScript");

        
        Gerente gerente = new Gerente("Carlos", "05176307618", "GER001", 8000);
        gerente.setFuncionario1(dev1);  
        gerente.setFuncionario2(dev2);

        // Exibir dados do gerente
        System.out.println("====== GERENTE ======");
        System.out.println("Nome: " + gerente.getNome());
        System.out.println("CPF: " + gerente.getCpf());
        System.out.println("Matrícula: " + gerente.getMatricula());
        System.out.println("Salário: R$ " + gerente.getSalario());

        // Exibir dados dos desenvolvedores 
        System.out.println("\n====== FUNCIONÁRIOS GERENCIADOS ======");

        System.out.println("\n--- Desenvolvedor 1 ---");
        System.out.println("Nome: " + dev1.getNome());
        System.out.println("CPF: " + dev1.getCpf());
        System.out.println("Matrícula: " + dev1.getMatricula());
        System.out.println("Salário: R$ " + dev1.getSalario());
        System.out.println("Linguagens: " + dev1.getLinguagem1() + ", " + dev1.getLinguagem2());

        System.out.println("\n--- Desenvolvedor 2 ---");
        System.out.println("Nome: " + dev2.getNome());
        System.out.println("CPF: " + dev2.getCpf());
        System.out.println("Matrícula: " + dev2.getMatricula());
        System.out.println("Salário: R$ " + dev2.getSalario());
        System.out.println("Linguagens: " + dev2.getLinguagem1() + ", " + dev2.getLinguagem2());
    }
}
