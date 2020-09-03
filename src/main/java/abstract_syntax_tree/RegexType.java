/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_syntax_tree;

/**
 *
 * @author zofia
 */
public enum RegexType {
    KLEENE,
    PLUS,
    QUESTION_MARK,
    CONCAT,
    OR,
    LETTER_MACRO,
    NUMBER_MACRO,
    UNI_C,
    UNI_N,
    BI_N,
    BI_C,
    MULTI_C,
    MULTI_N,
}
