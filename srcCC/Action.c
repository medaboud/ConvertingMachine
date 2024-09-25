//
// Created by Mohamed on 11/1/2022.
//

#include <stdbool.h>
#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include <ctype.h>

/////////////////////////// Future Struct ///////////////////////////
struct interim
{
    double p;
    double s;
    double v;
};


////////////////////////////////////// Actions ///////////////////////////////
void valueIsDigitAction(struct interim *result, char c) //1
{
    result->v = c - '0';
}
//Example function of what it should look like with pointer being passed in
void valueIsDigitAct(struct interim *result, char c)
{
    //interim->v = c - '0';
}

void negateAction(struct interim *result, char c)  //2
{
    result->s = -1;

}

void noAction(struct interim *result, char c) //3
{
    // nothing to be done;
}

void startFraction(struct interim *result, char c)  //4
{
    result->p = 0.1;
}

void continuingIntegerAction(struct interim *result, char c) //5
{
    result->v = 10 * result->v + c - '0';
}

void continuingFractionAction(struct interim *result, char c) //6
{
    result->v = result->v + (result->p * (c - '0'));
    result->p = result->p / 10;
}
// Changed type to the int, so it can return the equation s * v
void endOfCharacterAction(struct interim *result, char c) //7
{
    // return interim->s * interim->v;
}

void doneAction(struct interim *result, char c)
{
    result->v = 0;
}
///////////////////////////////////////// Verifiers /////////////////////////
/////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////

bool digitInputVerifier(char c)
{
    //returns a nonzero number if true, 0 if false
    if (isdigit(c) != 0)
    {
        return true;
    }
    return false;
}
bool minusInputVerifier(char c)
{
    // 45 is the ASCII value for a hyphen
    if (c ==  45)
    {
        return true;
    }
    return false;
}
bool periodInputVerifier(char c)
{
    // 46 is the ASCII value for a hyphen
    if (c ==  46)
    {
        return true;
    }
    return false;
}
bool plusInputVerifier(char c)
{
    // 43 is the ASCII value for a plus sign
    if (c ==  43)
    {
        return true;
    }
    return false;
}
bool nullTermInputVerifier(char c)
{
    // \0 is null terminator
    if(c == '\0')
    {
        return true;
    }
    return false;
}


////////////////////////////////////////Converting Machine ////////////////////////


enum State{START, INTEGER, DECIMAL, END};
enum State state;

struct Edge
{
    enum State currentState;
    void (* action)(struct interim *result, char);
    bool (* inputVerifier)(char);
    enum State nextState;
} Edge;


struct Edge machine[] = {
        {START, &valueIsDigitAction, &digitInputVerifier, INTEGER},
        {START, &negateAction, &minusInputVerifier, INTEGER},
        {START, &noAction, &plusInputVerifier, INTEGER},
        {START, &startFraction, &periodInputVerifier, DECIMAL},
        // {State.START, , ,State.END}, //bad character at start
        {INTEGER, &continuingIntegerAction, &digitInputVerifier, INTEGER},
        {INTEGER, &startFraction, &periodInputVerifier, DECIMAL},
        // {State.INTEGER, , ,State.END}, //bad character at middle or end
        {INTEGER, &endOfCharacterAction , &nullTermInputVerifier, END}, //reach the end of number ('\0')
        {DECIMAL, &continuingFractionAction, &digitInputVerifier, DECIMAL},
        // {State.DECIMAL, , ,State.END}, //bad character at middle or end
        {DECIMAL, &endOfCharacterAction , &nullTermInputVerifier, END} //reach the end of number ('\0')
};








///////////////////////////////////// Conversion Process //////////////////////////////
/////////////////////////////////////////////////////////////////////////////
struct Edge searchForEdge(enum State currentState, char ch)
{
    int counter = 0;
    struct Edge edge = machine[counter];

    while((edge.currentState != currentState
           || !edge.inputVerifier(ch))
          && counter < 9)
    {
        counter++;
        if (counter < 9) {
            edge = machine[counter];
        } else {
            edge.currentState = END;
            edge.nextState = END;
            edge.action = &doneAction;
        }
    }

    return edge;
}


double parse(char *text)
{
    int counter = 0;
    enum State currentState = START;
    struct interim *result;
    result->v = 0;
    result->s = 1;
    result->p = 0;

    char* temp = text;
    char ch[strlen(text)+1];
    ch[strlen(text)] = '\0'; ///////////////

    for(int i =0 ; i < strlen(text); i++)
    {
        ch[i] = *temp;
        temp++;
    }

    struct Edge edge;

    while(counter < strlen(text))
    {

        edge = searchForEdge(currentState, ch[counter]);

/******/
        edge.action(result, ch[counter]);
        currentState = edge.nextState;

        counter++;
    }

    if (result->v * result->s == 0)
    {
        return 0;
    }
    return result->v * result->s;
}



int main()
{

    printf("start\n");
    printf("test integers\n");
    printf("%f\n", parse("21"));
    printf("%f\n", parse("-21"));

    printf("test floats\n");
    printf("%f\n", parse("21.61"));
    printf("%f\n", parse("-21.61"));

    printf("test letters\n");
    printf("%f\n", parse("a"));
    printf("%f\n", parse("a21.61"));
    printf("%f\n", parse("21.61a"));

    printf("test bad symbols\n");
    printf("%f\n", parse("-45a"));
    printf("%f\n", parse("21.61-"));
    printf("%f\n", parse("%"));
    printf("%f\n", parse("%21.61"));
    printf("%f\n", parse("21.61%"));
    printf("end");
}
