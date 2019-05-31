#include <stdio.h>
#include "Q3.h"

int num_moves[15] = {2,2,2,4,4,4,4,4,4,6,6,6,6,6,6};
int moves[15][6] = {
{1,2}, // stack B and C empty
{3,4}, // stack A and C empty 
{5,6}, // stack A and B empty
{3,4,5,6}, // stack A empty 
{3,4,5,6}, // stack A empty 
{1,2,5,6}, // stack B empty 
{1,2,5,6}, // stack B empty 
{1,2,3,4}, // stack C empty 
{1,2,3,4}, // stack C empty
{1,2,3,4,5,6},
{1,2,3,4,5,6},
{1,2,3,4,5,6},
{1,2,3,4,5,6},
{1,2,3,4,5,6},
{1,2,3,4,5,6}
};

int count = 0;
int theAlgo[15];

int total_algos=0;

int work = 0;

void GenAlgos(int t)
{
	int i;

	if(t == 15)
	{

		total_algos++;
		if(doThis(theAlgo) == 1)
		{
			work ++;
		}


	}
	else
	{
		for(i = 0; i < num_moves[t]; i ++)
		{
			theAlgo[t] = moves[t][i];
			GenAlgos(t+1);
		}
	}
}



int main()
{
	GenAlgos(0);
	work = 0;
	printf("total algos %d\n", total_algos);
	printf("work count : %d\n",work);
}


/*
Output 

starting stacks = 12
success = 34152192
time taken 10 minutes


starting staks = 60
success = 255492
time taken 1 hour 25 minutes

starting stacks = 360
success = 45040
time = 5 hours 6 minutes

total algorithms = 1528823808

*/
