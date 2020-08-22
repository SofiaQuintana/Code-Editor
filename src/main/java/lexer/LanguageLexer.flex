package lexer;
import java_cup.runtime.*;
import parser.sym;
import environment.GlobalError;
import java.util.LinkedList;

%public
%class LanguageLexer
%cup
%cupdebug
%line
%column
%states JAVA_CODE, REG_EX, SYM, GRAMMAR

/*Rangos*/space
Letter = [a-zA-Z]
LowerCase = [a-z]
UpperCase = [A-Z]
Number = [0-9]
LineTerminator = \r|\n|\r\n 
WhiteSpace = {LineTerminator} | [ \t\f\b]
Int = ({Number})({Number})*
string = "\"" [^*] ~"\""
Id = ({Letter})({Letter | Number})*
InputCharacter = [^\r\n]
TraditionalCommentary = "/*" [^*] ~"*/"
BasicCommentary = "//" {InputCharacter}* {LineTerminator}?
Commentary = {TraditionalCommentary} | {BasicCommentary}

% {

    LinkedList<GlobalError> errors;

    public LanguageLexer(java.io.Reader in, LinkedList<GlobalError> errors) {
        this.zzReader = in;
        this.errors = errors;
    }

    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private void printToken(String token){
        System.out.println(token);
    }

    private void lexical_error(String value, int line, int column) {
        GlobalError error = new GlobalError("lexico", value, "Caracter Invalido", line, column);
        errors.add(error);
    }

% } 

<YYINITIAL> {
    "nombre"                            { return symbol(sym.NOMBRE, "nombre"); }
    "version"                             { return symbol(sym.VERSION, "version"); }
    "autor"                                { return symbol(sym.AUTOR, "autor"); }
    "lanzamiento"                     { return symbol(sym.LANZAMIENTO, "lanzamiento"); }
    "extension"                        { return symbol(sym.EXTENSION, "extension"); }  
    "."                                        { return symbol(sym.DOT, "."); }
    ";"                                        { return symbol(sym.SEMICOLON, ";"); }
    ":"                                        { return symbol(sym.COLON, ":"); }
    "%%"                                   { yybegin(JAVA_CODE); return symbol(sym.SEPARATOR, "%%"); } 
    {Commentary}                   { /*Do nothing*/ }
    { Int }                                   { return symbol(sym.INT, Integer.parseInt(yytext())); }
    { Id }                                    { return symbol(sym.ID, yytext()); }
    { WhiteSpace }                   { /*Do nothing*/ }
    [^]                                        { lexical_error((yytext(), yyline+1, yycolumn+1);
}

<JAVA_CODE> {
    "%%"                                     { yybegin(REG_EX); return symbol(sym.SEPARATOR, "%%"); }
    { WhiteSpace }                     { return symbol(sym.REGEX, yytext()); }
    [^]                                          {  return symbol(sym.JAVAC, yytext(); }
}

<REG_EX> {
    "?"                                       { return symbol(sym.QUESTION_MARK, "?"); }
    "*"                                       { return symbol(sym.ASTERISK, "*"); }
    "+"                                       { return symbol(sym.PLUS, "+"); }
    "|"                                        { return symbol(sym.PIPE, "|"); }
    "("                                        { return symbol(sym.ROUNDB_O, "("); }
    ")"                                        { return symbol(sym.ROUNDB_C, ")"); }
    "["                                        { return symbol(sym.SQUAREB_O, "["); }
    "]"                                        { return symbol(sym.SQUAREB_C, "]"); }
    "%%"                                   { yybegin(SYM); return symbol(sym.SEPARATOR, "%%"); }
    "\t"                                     { return symbol(sym.TAB, "\t"); }
    "\n"                                    { return symbol(sym.NEW_LINE, "\n"); }
    "\b"                                    { return symbol(sym.SPACE, "\b"); }
    "[0-9]"                                 { return symbol(sym.NUMBER_RANK, "[0-9]"); }
    "[a-z]"                                  { return symbol(sym.LETTER_RANK, "[a-z]"); }
    "="                                       { return symbol(sym.EQUAL, "="); }
     ";"                                       { return symbol(sym.SEMICOLON, ";"); }
    "&"                                       { return symbol(sym.AMPERSAND, "&"); }
    { Id }                                    { return symbol(sym.ID, yytext()); }
    {Commentary}                   { /*Do nothing*/ }
    { WhiteSpace }                   { /*Do nothing*/ }
    { string }                             {  return symbol(sym.STRING, yytext(); }
    [^]                                        { lexical_error((yytext(), yyline+1, yycolumn+1);
}

<SYM> {
    "terminal"                           { return symbol(sym.TERMINAL, "terminal"); }
    "no"                                     { return symbol(sym.NO, "no"); }
    "entero"                              { return symbol(sym.ENTERO, "entero"); }
    "real"                                   { return symbol(sym.REAL, "real"); }
    "cadena"                             { return symbol(sym.CADENA, "cadena"); }
    ","                                        { return symbol(sym.COMMA, ","); }
    ";"                                        { return symbol(sym.SEMICOLON, ";"); }
    "%%"                                   { yybegin(GRAMMAR); return symbol(sym.SEPARATOR, "%%"); }
    ({LowerCase})+                  { return symbol(sym.LOWER_C, yytext()); }
    ({UpperCase})+                  { return symbol(sym.UPPER_C, yytext()); }
    {Commentary}                   { /*Do nothing*/ }
    { WhiteSpace }                   { /*Do nothing*/ }
    [^]                                        { lexical_error((yytext(), yyline+1, yycolumn+1);
}

<GRAMMAR> {
    "RESULT"                            { return symbol(sym.RESULT, "RESULT"); }
    ":"                                        { return symbol(sym.COLON, ":"); }
    "::"                                       { return symbol(sym.DOUBLE_COLON, "::"); }
    "="                                       { return symbol(sym.EQUAL, "="); }
    "{"                                        { return symbol(sym.CURLYB_O, "{"); }
    "}"                                        { return symbol(sym.CURLYB_C, "}"); }
    { Id }                                    { return symbol(sym.ID, yytext()); }
    ({LowerCase})+                  { return symbol(sym.LOWER_C, yytext()); }
    ({UpperCase})+                  { return symbol(sym.UPPER_C, yytext()); }
    {Commentary}                   { /*Do nothing*/ }
    { WhiteSpace }                   { /*Do nothing*/ }
    [^]                                        {  return symbol(sym.JAVAC, yytext(); }
}

 <<EOF>>                            { return symbol(sym.EOF); }