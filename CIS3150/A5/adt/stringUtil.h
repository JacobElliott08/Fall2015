#include <string.h>
#include <stdlib.h>
#include <stdio.h>

typedef char * string;

char * addCharToString(char *, char);
char ** toStringArray(char *, char, int *);
char ** addStringToArray(char ** , int * , char * );

int checkIfStringExists(char ** , int , char * );
