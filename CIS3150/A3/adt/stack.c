#include <stdio.h>
#include "stack.h"

int A[100], B[100], C[100], atop=1, btop=1, ctop=1;


int * getStackA()
{
	return A;
}

int * getStackB()
{
	return B;
}

int * getStackC()
{
	return C;
}

int * getATop()
{
	return &atop;
}
int * getBTop()
{
	return &btop;
}
int * getCTop()
{
	return &ctop;
}


void clearStackA()
{
	while(atop != 1)
	{
		Pop(A,getATop());
	}
}
void clearStackB()
{
	while(btop != 1)
	{
		Pop(B,getBTop());
	}
}
void clearStackC()
{
	while(ctop != 1)
	{
		Pop(C,getCTop());
	}
}
/*=======================================================
 Moves an element from the top of one stack to another.
 If moving from an empty stack report an error and
 return false = 0, otherwise return true = 1
=======================================================*/
int move(int m)
{
	/* m = 1, move from A to B */
	switch(m)
	{
		case 1:
			{
				int i = Pop(A,&atop);
				if(i != 0)
				{
					Push(B,&btop,i);
				}
				return 0;
			}
			break;

	/* m = 2, move from A to C */
		case 2:
			{
				int i = Pop(A,&atop);
				if(i != 0)
				{
					Push(C,&ctop,i);
				}
				return 0;
			}
			break;
	/* m = 3, move from B to A */
		case 3:
			{
				int i = Pop(B,&btop);
				if(i != 0)
				{
					Push(A,&atop,i);
				}
				return 0;
			}
			break;
	/* m = 4, move from B to C */
		case 4:
			{
				int i = Pop(B,&btop);
				if(i != 0)
				{
					Push(C,&ctop,i);
				}
				return 0;
			}
			break;
	/* m = 5, move from C to A */
		case 5:
			{
				int i = Pop(C,&ctop);
				if(i != 0)
				{
					Push(A,&atop,i);
				}
				return 0;
			}
			break;
	/* m = 6, move from C to B */
		case 6:
			{
				int i = Pop(C,&ctop);
				if(i != 0)
				{
					Push(B,&btop,i);
				}
				return 0;
			}
			break;
	}
	return 1;

}

/*Test the stack to see if it's in the order. n, n-1, n-2 ... 0*/
int TestStack(int x[], int top, int n)
{
	if(top - 1 != n)
	{
		return 0;
	}
	int i = 0;
	for(i = n; i > 0; i --)
	{	
		if((x[n-i + 1]) != i)
		{
			return 0;
		}
	}

	return 1;
}

/*Pop off stack x and decrement the top, return the element that was popped.*/
int Pop(int x[], int * top)
{
	if(*top == 1)
	{
		return 0;
	}

	(*top) --;
	int toReturn = x[*top];
	x[*top] = 0;
	return toReturn;
}


/*Push element v into stack x increment top.*/
void Push(int x [], int * top, int v)
{
	x[*top] = v;
	(*top) ++;
}