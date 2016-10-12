import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class HelloRunner
{
    public static void main( String[] args) throws Exception 
    {

        MiniCLexer lexer = new MiniCLexer(new ANTLRFileStream("test.c"));
        CommonTokenStream tokens = new CommonTokenStream(lexer); 
        MiniCParser parser = new MiniCParser(tokens);
        ParseTree tree = parser.program(); // begin parsing at rule 'r'
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}