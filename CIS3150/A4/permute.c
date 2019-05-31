#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "adt/stringUtil.h"
#include "Q3.h"

char ** alreadyDone = NULL;
int doneSize = 0;

int * algo;

void permute(char * array, int currentPosition, int size, int * complete)
{
    int i;

    if (currentPosition == size)
    {
        if(checkIfStringExists(alreadyDone,doneSize,array) == 0)
        {
            /*printf("%s\n", array);*/
            clearStackA();
            clearStackB();
            clearStackC();
            /*printf("here\n");*/

            if(runAlgo(array,(size-1)) == 1)
            {
                (*complete) ++;
            }
            alreadyDone = addStringToArray(alreadyDone,&doneSize,array);
        }     

    }
    else
    {
        for (i = currentPosition; i <= size; i++)
        {
            swap((array+currentPosition), (array+i));
            permute(array, currentPosition+1, size,complete);
            swap((array+currentPosition), (array+i)); 
        }
    }
}


void swap(char * first, char * second)
{
    char temp;
    temp = * first;
    * first = * second;
    * second = temp;
}


int doThis(int * theAl)
{
    int i = 0;
    int n = 3; // 2
    int complete = 0;
    char * array = NULL;    
    alreadyDone = NULL;
    doneSize = 0;
    algo = theAl;
    array = addCharToString(array,'0');
    for(i = 0; i < n; i ++)
    {
        array = addCharToString(array,(i +'0'));
    }

    permute(array,0,n,&complete);

    free(array);
    if(complete == perm(n+1))
    {
        return 1;
    }
    return 0;
}








/*test the stack config against the algorithm*/
int runAlgo(char * string, int n)
{
    int len = strlen(string);
    int i = 0;
    char c = string[i];

    /*parse the input string (ex 010123) into the three stacks using the zeros as delim*/
    while(c != '0' && i < len)
    {
        Push(getStackA(),getATop(),atoi(&c));
        i ++;
        c = string[i];
    }

    i ++;
    c = string[i];
    while(c != '0' && i < len)
    {
        Push(getStackB(),getBTop(),atoi(&c));
        i ++;
        c = string[i];
    }

    i ++;
    c = string[i];
    while(c != '0' && i < len)
    {
        Push(getStackC(),getCTop(),atoi(&c));
        i ++;
        c = string[i];
    }

    /*Swap until iter < totalPerm or the stack is in proper order*/
    int iter = 0;
    int totalPerm = perm(n+2);
    while(iter < totalPerm && TestStack(getStackA(), (* getATop()), n) == 0)
    {
        shuffle();
        iter ++;
    }


    return (TestStack(getStackA(), (*getATop()),n));

}

/*Shuffle function*/
void shuffle()
{
    int aVal = 0;
    int bVal = 0;
    int cVal = 0;

    aVal = Pop(getStackA(),getATop());
    bVal = Pop(getStackB(),getBTop());
    cVal = Pop(getStackC(),getCTop());

    if(aVal != 0)
    {
        Push(getStackA(),getATop(),aVal);
    }

    if(bVal != 0)
    {
        Push(getStackB(),getBTop(),bVal);
    }

    if(cVal != 0)
    {
        Push(getStackC(),getCTop(),cVal);   
    }


    /*1: B and C empty*/
    if(aVal != 0 && bVal == 0 && cVal == 0)
    {
        /*printf("Here 1\n");*/
        move(algo[0]);
    }
    
    /*2: A and C empty*/
    else if(aVal == 0 && bVal != 0 && cVal == 0)
    {
        /*printf("Here 2\n");*/
        move(algo[1]);
    }
    
    /*3: A and B empty*/
    else if(aVal == 0 && bVal == 0 && cVal != 0)
    {
        /*printf("Here 3\n");*/
        move(algo[2]);
    }

    /*4: A empty and b<c*/
    else if(aVal == 0 && bVal < cVal)
    {
        /*printf("Here 4\n");*/
        move(algo[3]);
    }

    /*5: A empty and c<b*/
    else if(aVal == 0 && bVal > cVal)
    {
        /*printf("Here 5\n");*/
        move(algo[4]);
    }

    /*6: B empty and a<c*/
    else if(bVal == 0 && aVal < cVal)
    {
        /*printf("Here 6\n");*/
        move(algo[5]);
    }

    /*7: B empty and c<a*/
    else if(bVal == 0 && aVal > cVal)
    {
        /*printf("Here 7\n");*/
        move(algo[6]);
    }

    /*8: C empty and a<b*/
    else if(aVal < bVal && cVal == 0)
    {
        /*printf("Here 8\n");*/
        move(algo[7]);
    }

    /*9: C empty and b<a*/
    else if(aVal > bVal && cVal == 0)
    {
        /*printf("Here 9\n");*/
        move(algo[8]);
    }
    
    /*10: a < b < c*/
    else if(aVal < bVal && bVal < cVal)
    {
        /*printf("Here 10\n");*/
        move(algo[9]);
    }
    
    /*11: a < c < b*/
    else if(aVal < cVal && cVal < bVal)
    {
        /*printf("Here 11\n");*/
        move(algo[10]);
    }
    
    /*12: b < a < c*/
    else if(bVal < aVal && aVal < cVal)
    {
        /*printf("Here 12\n");*/
        move(algo[11]);
    }
    
    /*13: b < c < a*/
    else if (bVal < cVal && cVal < aVal)
    {
        /*printf("Here 13\n");*/
        move(algo[12]);
    }

    /*14: c < a < b*/
    else if(cVal < aVal && aVal < bVal)
    {
        /*printf("Here 14\n");*/
        move(algo[13]);
    }
    
    /*15: c < b < a*/
    else if(cVal < bVal && bVal < aVal)
    {
        /*printf("Here 15\n");*/
        move(algo[14]);
    }
    
}

/*calc the factorial*/
int perm(int i)
{
    int sum = 1;
    int a = 0;
    for(a = i; a > 0; a --)
    {
        sum *= a;
    }
    return (sum/2);
}   