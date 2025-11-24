package codigoGals;

import java.util.Stack;

public class Semantico implements Constants
{
    Stack<String> pilha_tipos = new Stack<String>();
    private String codigo = "";

    
    public String getCodigo() {
        return codigo;
    }

    public void executeAction(int action, Token token)	throws SemanticError
    {
        //fazer switch case para cada açao semantica
        switch(action){
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
        }
        
    }	

    void acao100(){
        codigo = ".assembly extern mscorlib {}\n" +
                       ".assembly _programa{}\n" +
                       ".module _programa.exe\n" +
                       "\n" +
                       ".class public _unica{\n" +
                       ".method static public void _principal(){\n" +
                       ".entrypoint\n";
    }

    void acao101(){
        codigo += "ret\n" +
                        "}\n" +
                        "}\n";
    }
    void acao102(){
        if(pilha_tipos.peek().equals("int64") && pilha_tipos.size() > 0){
            pilha_tipos.pop();
            codigo += "conv.i8\n";
            codigo += "call void [mscorlib]System.Console::Write(int64)\n";
        }else if(pilha_tipos.peek().equals("float64") && pilha_tipos.size() > 0){
            pilha_tipos.pop();
            codigo += "call void [mscorlib]System.Console::Write(float64)\n";
        }else if(pilha_tipos.peek().equals("string") && pilha_tipos.size() > 0){
            pilha_tipos.pop();
            codigo += "call void [mscorlib]System.Console::Write(string)\n";
        }else if(pilha_tipos.peek().equals("bool") && pilha_tipos.size() > 0){
            pilha_tipos.pop();
            codigo += "call void [mscorlib]System.Console::Write(bool)\n";
        }
    }

    void acao103(Token token){
        pilha_tipos.push("int64");
        codigo += "ldc.i8 " + token.getLexeme() + "\n"
                + "conv.r8\n";
    }

    void acao104(Token token){
        pilha_tipos.push("float64");
        codigo += "ldc.r8 " + token.getLexeme() + "\n";
    }

    void acao105(Token token){
        pilha_tipos.push("string");
        codigo += "ldstr" + token.getLexeme() + "\n";
    }
}
