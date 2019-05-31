#include "adt/stringUtil.h"

int isValid(string theInput);
int test(string theInput, int * array);

int main()
{
	string array = NULL;
	int size = 0;
	int iterations = 1;
	int i = 0;
	int a = 0;
	int intArr[10] = {59,25,27,3,2,15,9,30,21,19};
	/*//1,2,3,4,5,14,16,17,31
	//1,2,3,4,5,6,7,8,9
	//1,2,3,4,5,6,7,8,9,10
	//1,1,2,2,3,3,4,4,5,5,6,6,7,7
	//25,27,3,2,15,9,30,21,19,56
	//59,25,27,3,2,15,9,30,21,19*/
	printf("> ");

	scanf("%d",&size);
	getchar();


	/*Calculate the total number of iterations 3^n*/
	for(i = 0; i < size; i ++)
	{
		iterations = iterations * 3;
	}

	/*Init the string*/
	for(i = 0; i < size; i ++)
	{
		array = addCharToString(array,'1');
	}


	int counter = 0;
	/**/
	printf("Size : %d Iterations : %d\n",size,iterations);


	/*Calculating all the permutations using an iterative approach.*/
	for(i = 0; i < (iterations); i ++)
	{
		if(isValid(array))
		{
			if(test(array, intArr))
			{
				printf("Working configuration : %s\n",array);
			}
			counter++;
		}

		for(a = (size - 1); a >= 0; a --)
		{	
			if(array[a] == '3')
			{
				array[a] = '1';
			}
			else
			{
				array[a] ++;
				break;
			}
		}

	}
	return 0;
}


/*Determines if the current string is valid
IN : theString
OUT: 1 if valid 0 else*/
int isValid(string theInput)
{
	int i = 0;
	int size = strlen(theInput);
	int hasSeenOne = 0;
	int hasSeenTwo = 0;


	for(i = 0; i < size; i ++)
	{
		if(theInput[i] == '1')
		{
			hasSeenOne = 1;
		}
		else if(theInput[i] == '2')
		{
			if(hasSeenOne == 0)
			{
				return 0;
			}
			else
			{
				hasSeenTwo = 1;
			}
		}
		else
		{
			if(hasSeenTwo == 0)
			{
				return 0;
			}
		}
	}
	return 1;

}


/*Tests the sums to see if they're all equal
IN: the configuration for the subsets, the total set
OUT: 1 if valid 0 else*/
int test(string theInput, int * array)
{
	int i = 0;
	int size = strlen(theInput);
	int stackOne = 0;
	int stackTwo = 0;
	int stackThree = 0;


	for(i = 0; i < size; i ++)
	{
		if(theInput[i] == '1')
		{
			stackOne += array[i];
		}
		else if (theInput[i] == '2')
		{
			stackTwo += array[i];
		}
		else
		{
			stackThree += array[i];
		}
	}

	if(stackOne == stackTwo && stackTwo == stackThree)
	{
		return 1;
	}
	return 0;
}	



/*
n= 4 : 14

n = 5 : 41

n = 6 : 122

n = 7 : 365

n = 8 : 1094*/



/*
1,2,3,4,5,14,16,17,31
Size : 9 Iterations : 19683
Working configuration : 111112123
Working configuration : 122221123


1,2,3,4,5,6,7,8,9
Size : 9 Iterations : 19683
Working configuration : 111112332
Working configuration : 111222331
Working configuration : 112123213
Working configuration : 112213123
Working configuration : 121123123
Working configuration : 121211332
Working configuration : 122212331
Working configuration : 123231312
Working configuration : 123312231

1,2,3,4,5,6,7,8,9,10
Size : 10 Iterations : 59049

1,1,2,2,3,3,4,4,5,5,6,6,7,7
Size : 14 Iterations : 4782969

25,27,3,2,15,9,30,21,19,56
Size : 10 Iterations : 59049

59,25,27,3,2,15,9,30,21,19
Size : 10 Iterations : 59049
Working configuration : 1222121333
Working configuration : 1233121233

*/
