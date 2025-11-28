package codigoGals;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.management.RuntimeErrorException;

public class Semantico implements Constants {
    Stack<String> pilha_tipos = new Stack<String>();
    private String codigo = "";

    // ****
    String operador_relacional = "";   
    String tipo = ""; 
    List<String> lista_identificadores = new ArrayList<>();
    // ****

    public String getCodigo() {
        return codigo;
    }

    public void executeAction(int action, Token token) throws SemanticError {
        // fazer switch case para cada açao semantica
        switch (action) {
            // case 100:
            //     acao100();
            //     break;
            // case 101:
            //     acao101();
            //     break;
            // case 102:
            //     acao102();
            //     break;
            // case 103:
            //     acao103(token);
            //     break;
            // case 104:
            //     acao104(token);
            //     break;
            // case 105:
            //     acao105(token);
            //     break;
            //  case 106:
            //  acao106(token);
            //   break;
            //  case 107:
            //  acao107(token);
            //  break;
            //  case 108:
            //  acao108(token);
            //  break;
            //  case 109:
            //  acao109(token);
            //  break;
            //  case 110:
            //  acao110(token);
            //  break;
            //  case 111:
            //  acao111(token);
            //  break;
            //  case 112:
            //  acao112(token);
            //  break;
            //  case 113:
            //  acao113(token);
            //  break;
            //  case 114:
            //  acao104(token);
            //  break;
            //  case 115:
            //  acao115(token);
            //  break;
            //  case 116:
            //  acao116(token);
            //  break;
            //  case 117:
            //  acao117(token);
            //  break;
            //  case 118:
            //  acao118();
            //  break;
            //  case 119:
            //  acao119(token);
             
        }

    }

    void acao100() {
        codigo = ".assembly extern mscorlib {}\n" +
                ".assembly _programa{}\n" +
                ".module _programa.exe\n" +
                "\n" +
                ".class public _unica{\n" +
                ".method static public void _principal(){\n" +
                ".entrypoint\n";
    }

    void acao101() {
        codigo += "ret\n" +
                "}\n" +
                "}\n";
    }

    void acao102() {
        if (pilha_tipos.size() > 0) {
            String tipo = pilha_tipos.pop();

            if (tipo.equals("int64")) {
                codigo += "conv.i8\n" + "call void [mscorlib]System.Console::Write(int64)\n";
            } else
                codigo += "call void [mscorlib]System.Console::Write(" + tipo + ")\n";

        } else
            throw new RuntimeException("Pilha vazia");

    }

    // void acao118(Token token) {
    // }

    void acao103(Token token) {
        pilha_tipos.push("int64");
        codigo += "ldc.i8 " + token.getLexeme() + "\n"
                + "conv.r8\n";
    }

    void acao104(Token token) {
        pilha_tipos.push("float64");
        codigo += "ldc.r8 " + token.getLexeme() + "\n";
    }

    void acao105(Token token) {
        pilha_tipos.push("string");
        codigo += "ldstr " + token.getLexeme() + "\n";
    }

    void acao115(Token token) {
        pilha_tipos.push("bool");
        codigo += "ldc.i4.1\n";
    }
    
    void acao116(Token token) {
        pilha_tipos.push("bool");
        codigo += "ldc.i4.0\n";
    }

    // void acao110(Token token){

    // }

    void acao106(Token token) {
        String tipo2 = pilha_tipos.pop();
        String tipo1 = pilha_tipos.pop();

        if (tipo1.equals("int64") && tipo2.equals("int64")) {
            pilha_tipos.push("int64");
            codigo += "add\n";
        } else 
            pilha_tipos.push("float64");
            codigo += "add\n";
    }

    void acao107(Token token) {
        String tipo2 = pilha_tipos.pop();
        String tipo1 = pilha_tipos.pop();

        if (tipo1.equals("int64") && tipo2.equals("int64")) {
            pilha_tipos.push("int64");
            codigo += "sub\n";
        } else 
            pilha_tipos.push("float64");
            codigo += "sub\n";
    }

    void acao108(Token token) {
        String tipo2 = pilha_tipos.pop();
        String tipo1 = pilha_tipos.pop();

        if (tipo1.equals("int64") && tipo2.equals("int64")) {
            pilha_tipos.push("int64");
            codigo += "mul\n";
        } else 
            pilha_tipos.push("float64");
            codigo += "mul\n";
    }

    void acao109(Token token) {
        pilha_tipos.pop();
        pilha_tipos.pop();

        pilha_tipos.push("float64");
        codigo += "div\n";
    }

    void acao111(Token token){
        operador_relacional = token.getLexeme();
    }

    void acao112(Token token){
        pilha_tipos.pop();
        pilha_tipos.pop();

        if(operador_relacional.equals("==")){
            codigo += "ceq\n";
        } else if(operador_relacional.equals("~=")){
            codigo += "ceq\n"+
                      "ldc.i4.1\n"+
                      "xor\n";
        } else if(operador_relacional.equals("<")){
            codigo += "clt\n";
        } else if(operador_relacional.equals(">")){
            codigo += "cgt\n";
        }
    }

    void acao117(Token token) {
        codigo += "ldc.i4.1\n"+
                  "xor\n";
    }

    // void acao113(Token token) { //perguntar a professora sobre o 113 e 114
    //     String tipo2 = pilha_tipos.pop();
    //     String tipo1 = pilha_tipos.pop();

    //     if (tipo1.equals("bool") && tipo2.equals("bool")) {
    //         pilha_tipos.push("bool");
    //         codigo += "and\n";
    //     } 
    // }

    // void acao114(Token token) {
    //     String tipo2 = pilha_tipos.pop();
    //     String tipo1 = pilha_tipos.pop();

    //     if (tipo1.equals("bool") && tipo2.equals("bool")) {
    //         pilha_tipos.push("bool");
    //         codigo += "or\n";
    //     } 
    // }

    // void acao120(Token token) {
    //     tipo = token.getLexeme();
    // }

    // void acao121(Token token) {
    //     lista_identificadores.add(token.getLexeme());
    // }

    // // void acao119(Token token) {

    // // }

    // void acao122(Token token) {
    //     String tipo_expressao = pilha_tipos.pop();

    //     if(tipo_expressao.equals("int64")) {
    //         codigo += "conv.i8\n";
    //     }

    //     String id = lista_identificadores.getFirst();
    //     codigo += "stloc " + id + "\n";
    //     lista_identificadores.remove(0);
    // }   
    
    // void acao123(Token token) {
    //     String id = token.getLexeme();
        
    //     if(id.equals("bool")){
    //         //deve lançar um erro TEM QUE FAZER ISSO AQUI***
    //     }else
    //         codigo += "call "+ id +" [mscorlib] System." + id.substring(0,1).toUpperCase() + id.substring(1) + "::Parse(string)\n";
    //         codigo += "stloc " + id + "\n";
    // }

    // void acao124(Token token) {
    //     codigo += "ldstr " + token.getLexeme() + "\n";
    //     codigo += "call void [mscorlib]System.Console::Write(string)\n";
    // }

    // void acao130(Token token) {

    // }

    // void acao125(Token token) {

    // }

    // void acao127(Token token) {

    // }

    // void acao126(Token token) {

    // }

    // void acao128(Token token) {

    // }

    // void acao129(Token token) {

    // }
}
