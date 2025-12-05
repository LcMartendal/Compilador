package codigoGals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Semantico implements Constants {
    Stack<String> pilha_tipos = new Stack<String>();
    private String codigo_objeto = "";
    String operador_relacional = "";
    String tipo = "";
    Stack<String> pilha_rotulos = new Stack<>();
    List<String> lista_identificadores = new ArrayList<>();
    HashMap<String, String> tabela_simbolos = new HashMap<>();

    public String getCodigo_objeto() {
        return codigo_objeto;
    }

    private int contLabel = 0;

    public void executeAction(int action, Token token) throws SemanticError {
        // fazer switch case para cada açao semantica
        switch (action) {
            case 100:
                acao100();
                break;
            case 101:
                acao101();
                break;
            case 102:
                acao102();
                break;
            case 103:
                acao103(token);
                break;
            case 104:
                acao104(token);
                break;
            case 105:
                acao105(token);
                break;
            case 106:
                acao106(token);
                break;
            case 107:
                acao107(token);
                break;
            case 108:
                acao108(token);
                break;
            case 109:
                acao109(token);
                break;
            case 110:
                acao110(token);
                break;
            case 111:
                acao111(token);
                break;
            case 112:
                acao112(token);
                break;
            case 113:
                acao113(token);
                break;
            case 114:
                acao114(token);
                break;
            case 115:
                acao115(token);
                break;
            case 116:
                acao116(token);
                break;
            case 117:
                acao117(token);
                break;
            case 118:
                acao118();
                break;
            case 119:
                acao119();
                break;
            case 120:
                acao120(token);
                break;
            case 121:
                acao121(token);
                break;
            case 122:
                acao122(token);
                break;
            case 123:
                acao123(token);
                break;
            case 124:
                acao124(token);
                break;
            case 125:
                acao125(token);
                break;
            case 126:
                acao126(token);
                break;
            case 127:
                acao127(token);
                break;
            case 128:
                acao128(token);
                break;
            case 129:
                acao129(token);
                break;
            case 130:
                acao130(token);
                break;
            default:
                // ação não implementada
                throw new SemanticError("Ação semântica não implementada: " + action);
        }        
    }

    void acao100() {
        codigo_objeto = ".assembly extern mscorlib {}\n" +
                ".assembly _programa{}\n" +
                ".module _programa.exe\n" +
                "\n" +
                ".class public _unica{\n" +
                " .method static public void _principal(){\n" +
                "  .entrypoint\n";
    }

    void acao101() {
        codigo_objeto += "  ret\n" +
                " }\n" +
                "}\n";
    }

    void acao102() {
        if (pilha_tipos.size() > 0) {
            String tipo = pilha_tipos.pop();

            if (tipo.equals("int64")) {
                codigo_objeto += "  conv.i8\n" + "  call void [mscorlib]System.Console::Write(int64)\n";
            } else
                codigo_objeto += "  call void [mscorlib]System.Console::Write(" + tipo + ")\n";

        } else
            throw new RuntimeException("Pilha vazia");

    }

    void acao118() {
        codigo_objeto += "  call void [mscorlib]System.Console::WriteLine()\n";
    }

    void acao103(Token token) {
        pilha_tipos.push("int64");
        codigo_objeto += "  ldc.i8 " + token.getLexeme() + "\n"
                + "  conv.r8\n";
    }

    void acao104(Token token) {
        pilha_tipos.push("float64");
        codigo_objeto += "  ldc.r8 " + token.getLexeme() + "\n";
    }

    void acao105(Token token) {
        pilha_tipos.push("string");
        codigo_objeto += "  ldstr " + token.getLexeme() + "\n";
    }

    void acao115(Token token) {
        pilha_tipos.push("bool");
        codigo_objeto += "  ldc.i4.1\n";
    }

    void acao116(Token token) {
        pilha_tipos.push("bool");
        codigo_objeto += "  ldc.i4.0\n";
    }

    void acao110(Token token) { // não há necessidade de tirar e colocar tipo na pilha
        codigo_objeto += "  ldc.r8 -1\n"
                + "  mul\n";
    }

    void acao106(Token token) throws SemanticError {
        if (pilha_tipos.size() < 2)
            throw new SemanticError("Operador '+' com menos de 2 operandos ", token.getPosition());
        String tipo2 = pilha_tipos.pop();
        String tipo1 = pilha_tipos.pop();

        if (tipo1.equals("int64") && tipo2.equals("int64")) {
            pilha_tipos.push("int64");
        } else {
            pilha_tipos.push("float64");
        }
        codigo_objeto += "  add\n";
    }

    void acao107(Token token) throws SemanticError {
        if (pilha_tipos.size() < 2)
            throw new SemanticError("Operador '-' com menos de 2 operandos ", token.getPosition());
        String tipo2 = pilha_tipos.pop();
        String tipo1 = pilha_tipos.pop();

        if (tipo1.equals("int64") && tipo2.equals("int64")) {
            pilha_tipos.push("int64");
        } else {
            pilha_tipos.push("float64");
        }
        codigo_objeto += "  sub\n";
    }

    void acao108(Token token) throws SemanticError {
        if (pilha_tipos.size() < 2)
            throw new SemanticError("Operador '*' com menos de 2 operandos ", token.getPosition());
        String tipo2 = pilha_tipos.pop();
        String tipo1 = pilha_tipos.pop();

        if (tipo1.equals("int64") && tipo2.equals("int64")) {
            pilha_tipos.push("int64");
        } else {
            pilha_tipos.push("float64");
        }
        codigo_objeto += "  mul\n";
    }

    void acao109(Token token) throws SemanticError {
        if (pilha_tipos.size() < 2)
            throw new SemanticError("Operador '/' com menos de 2 operandos ", token.getPosition());
        pilha_tipos.pop();
        pilha_tipos.pop();

        pilha_tipos.push("float64");
        codigo_objeto += "  div\n";
    }

    void acao111(Token token) {
        operador_relacional = token.getLexeme();
    }

    void acao112(Token token) throws SemanticError {
        if (pilha_tipos.size() < 2)
            throw new SemanticError("Operador relacional com menos de 2 operandos ", token.getPosition());
        pilha_tipos.pop();
        pilha_tipos.pop();
        pilha_tipos.push("bool");
        if (operador_relacional.equals("==")) {
            codigo_objeto += "  ceq\n";
        } else if (operador_relacional.equals("~=")) {
            codigo_objeto += "  ceq\n" +
                    "  ldc.i4.1\n" +
                    "  xor\n";
        } else if (operador_relacional.equals("<")) {
            codigo_objeto += "  clt\n";
        } else if (operador_relacional.equals(">")) {
            codigo_objeto += "  cgt\n";
        }
    }

    void acao117(Token token) {
        codigo_objeto += "  ldc.i4.1\n" +
                "  xor\n";
    }

    void acao113(Token token) throws SemanticError {
        if (pilha_tipos.size() < 2)
            throw new SemanticError("Operador 'and' com menos de 2 operandos ", token.getPosition());
        String tipo2 = pilha_tipos.pop();
        String tipo1 = pilha_tipos.pop();

        if (!tipo1.equals("bool") || !tipo2.equals("bool")) {
            throw new SemanticError("Operador 'and' em operandos não booleanos", token.getPosition());
        }

        pilha_tipos.push("bool");
        codigo_objeto += "  add\n";
    }

    void acao114(Token token) throws SemanticError {
        if (pilha_tipos.size() < 2)
            throw new SemanticError("Operador 'or' com menos de 2 operandos ", token.getPosition());
        String tipo2 = pilha_tipos.pop();
        String tipo1 = pilha_tipos.pop();

        if (!tipo1.equals("bool") || !tipo2.equals("bool")) {
            throw new SemanticError("Operador 'or' em operandos não booleanos", token.getPosition());
        }

        pilha_tipos.push("bool");
        codigo_objeto += "or\n";
    }

    void acao120(Token token) {
        tipo = token.getLexeme();
    }

    void acao121(Token token) throws SemanticError {
        lista_identificadores.add(token.getLexeme());
    }

    void acao119() throws SemanticError {
        if (tipo == null || tipo.isEmpty()) {
            throw new SemanticError("Tipo não definido na declaração");
        }
        String tipoIL = "";
        switch (tipo) {
            case "int":
            case "pr_int":
                tipoIL = "int64";
                break;
            case "float":
            case "pr_float":
                tipoIL = "float64";
                break;
            case "string":
            case "pr_string":
                tipoIL = "string";
                break;
            case "bool":
            case "pr_bool":
                tipoIL = "bool";
                break;
            default:
                throw new SemanticError("Tipo não definido na declaração" + tipo);
        }
        for (String id : lista_identificadores) {
            if (tabela_simbolos.containsKey(id)) {
                throw new SemanticError("Identificador já declarado: " + id);
            }
            tabela_simbolos.put(id, tipoIL);
            codigo_objeto += "  .locals (" + tipoIL + " " + id + ")\n";
        }
        lista_identificadores.clear();
        tipo = "";
    }

    void acao122(Token token) throws SemanticError {
        if (lista_identificadores.isEmpty())
            throw new SemanticError("Nenhum identificador para atribuição", token.getPosition());
        if (pilha_tipos.isEmpty())
            throw new SemanticError("Expressão vazia na atribuição", token.getPosition());
        String tipoExpr = pilha_tipos.pop();
        if (tipoExpr.equals("int64")) {
            codigo_objeto += "  conv.i8\n";
        }
        String id = lista_identificadores.remove(lista_identificadores.size() - 1);
        if (!tabela_simbolos.containsKey(id)) {
            throw new SemanticError("Identificador não declarado em atribuição: " + id, token.getPosition());
        }
        codigo_objeto += "  stloc " + id + "\n";
    }

    void acao123(Token token) throws SemanticError {
        String id = token.getLexeme();
        if (!tabela_simbolos.containsKey(id)) {
            throw new SemanticError("Identificador não declarado em comando de entrada: " + id, token.getPosition());
        }
        String tipoId = tabela_simbolos.get(id);
        if (tipoId.equals("bool")) {
            throw new SemanticError(id + " inválido para comando de entrada", token.getPosition());
        }
        codigo_objeto += "  call string [mscorlib]System.Console::ReadLine()\n";
        if (tipoId.equals("int64")) {
            codigo_objeto += "  call int64 [mscorlib]System.Int64::Parse(string)\n";
        } else if (tipoId.equals("float64")) {
            codigo_objeto += "  call float64 [mscorlib]System.Double::Parse(string)\n";
        } else if (tipoId.equals("string")) {
        } else {
            throw new SemanticError("Tipo inválido em comando de entrada: " + tipoId, token.getPosition());
        }
        codigo_objeto += "  stloc " + id + "\n";
    }

    void acao124(Token token) {
        codigo_objeto += "  ldstr " + token.getLexeme() + "\n";
        codigo_objeto += "  call void [mscorlib]System.Console::Write(string)\n";
    }

    void acao130(Token token) throws SemanticError {
        String id = token.getLexeme();
        if (!tabela_simbolos.containsKey(id)) {
            throw new SemanticError("Identificador não declarado em expressão: " + id, token.getPosition());
        }
        String tipoId = tabela_simbolos.get(id);
        pilha_tipos.push(tipoId);
        codigo_objeto += "  ldloc " + id + "\n";
        if (tipoId.equals("int64")) {
            codigo_objeto += "  conv.r8\n";
        }
    }

    void acao125(Token token) throws SemanticError {
        if (pilha_tipos.isEmpty())
            throw new SemanticError("Expressão vazia em comando de seleção", token.getPosition());
        String tipoExpr = pilha_tipos.pop();
        if (!tipoExpr.equals("bool")) {
            throw new SemanticError("expressão incompatível em comando de seleção", token.getPosition());
        }
        String r = "L" + (contLabel++);
        codigo_objeto += "  brfalse " + r + "\n";
        pilha_rotulos.push(r);
    }

    void acao127(Token token) throws SemanticError {
        String novo2 = "L" + (contLabel++);
        codigo_objeto += "  br " + novo2 + "\n";
        if (pilha_rotulos.isEmpty())
            throw new SemanticError("Pilha de rótulos vazia em acao127", token.getPosition());
        String novo1 = pilha_rotulos.pop();
        codigo_objeto += novo1 + ":\n";
        pilha_rotulos.push(novo2);
    }

    void acao126(Token token) throws SemanticError {
        if (pilha_rotulos.isEmpty())
            throw new SemanticError("Pilha de rótulos vazia em acao126", token.getPosition());
        String r = pilha_rotulos.pop();
        codigo_objeto += r + ":\n";
    }

    void acao128(Token token) {
        String r = "L" + (contLabel++);
        codigo_objeto += r + ":\n";
        pilha_rotulos.push(r);
    }

    void acao129(Token token) throws SemanticError {
        if (pilha_tipos.isEmpty())
            throw new SemanticError("Expressão vazia em comando de repetição", token.getPosition());
        String tipoExpr = pilha_tipos.pop();
        if (!tipoExpr.equals("bool")) {
            throw new SemanticError("expressão incompatível em comando de repetição", token.getPosition());
        }
        if (pilha_rotulos.isEmpty())
            throw new SemanticError("Pilha de rótulos vazia em acao129", token.getPosition());
        String rot = pilha_rotulos.pop();
        codigo_objeto += "  brfalse " + rot + "\n";
    }
}
