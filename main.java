#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <math.h>


	void forsignedCase(char buffer[],char type[],int byte){
		int number;
		number = atoi(buffer);
	
		int binary[16];
		integerToBinary(number,binary,16);
		printArray(binary,16);
	
		int hexArray[4];
		convertHexArray(binary,hexArray,16);
	
		if(strcmp(type,"big") == 1){ 
			printLtl(hexArray,byte*8);
		} 
		else printBig(hexArray,16);
	
}

	void forunsignedCase(char buffer[],char type[],int byte){
		int number;
		number = atoi(buffer);
	
		int binary[16];
		integerToBinary(number,binary,16);
		printArray(binary,16);
		
		int hexArray[4];
		convertHexArray(binary,hexArray,16);
	
		if(strcmp(type,"big") == 1){
			printLtl(hexArray,byte*8);
		} 
		else printBig(hexArray,16);
}

	void integerToBinary(int decimal,int binary[],int numBits){
	int i = 0;
	int j = 0;
	
	while(i < numBits){
		binary[i] = 1 & (decimal >> i);
		i++;	
	}
		
	int temp[numBits];
	
	for(j = 0, i = numBits-1; j < numBits,i>= 0;j++,i--){
		temp[j] = binary[i];
	}
	
	for(j = 0; j < numBits; j++){ 
		binary[j] = temp[j];
	}
}

void printArray(int array[],int arraySize){
	int i = 0;
	while(i < arraySize){
		printf("%d ",array[i]);
		i++;	
	}
}

void convertHexArray(int array[],int hexxArray[],int bitSize){
	
	
	int power = 0, sum = 0;
	
	int count = 0;
	int j = (bitSize / 4) - 1;
	
	int hexArray[bitSize/4];
	
	int i = bitSize-1;
	while(i>=0){
		sum = sum + pow(2,power) *array[i];
		power = power + 1;
		count = count + 1;		
		if(count == 4){
			count = 0;
			power = 0;
			hexArray[j] = sum;
			j--;
			sum = 0;
		}
		i = i - 1;
	}
	i=0;
	while(i< bitSize/4){
		hexxArray[i] = hexArray[i];
		i++;
	}
}

void printBig(int hexArray[],int bit){
	int i = 0;
		FILE *file;
	 file = fopen("output.txt","w");
	int counter = 0;
	
	for(i = 0; i < bit/4; i+= 2){
	switch(hexArray[i]){
			case 10:
				printf("A"); 
				break;
			case 11:
				printf("B");
				break; 
			case 12:
				printf("C"); 
				break;
			case 13:
				printf("D"); 
				break;
			case 14:
				printf("E");
				break;
			case 15:
				printf("F"); 
				break;
			default:
				printf("%d",hexArray[i]);
			break;	 			
		}	
		 
		switch(hexArray[i+1]){
			case 10:
				printf("A"); 
				break;
			case 11:
				printf("B");
				break; 
			case 12:
				printf("C"); 
				break;
			case 13:
				printf("D"); 
				break;
			case 14:
				printf("E");
				break;
			case 15:
				printf("F"); 
				break;
			default:
				printf("%d",hexArray[i+1]);
				break;
			}
			
			printf(" ");
	}
	
		
	
	printf("\n");
}

void printLtl(int hexArray[],int bit){
	
		FILE *file;
	 file = fopen("output.txt","w");
	 
	int counter = 0;
	
	int i = 0;
	for(i = bit/4 - 2; i>=0;i -=2){
		if(counter == bit/ 8);	
	
		switch(hexArray[i]){
			case 10:
				printf("A"); 
				break;
			case 11:
				printf("B");
				break; 
			case 12:
				printf("C"); 
				break;
			case 13:
				printf("D"); 
				break;
			case 14:
				printf("E");
				break;
			case 15:
				printf("F"); 
				break;
			default:
				printf("%d",hexArray[i]);
				break;	 			
		}
		
		switch(hexArray[i+1]){
			case 10:
				printf("A"); 
				break;
			case 11:
				printf("B");
				break; 
			case 12:
				printf("C"); 
				break;
			case 13:
				printf("D"); 
				break;
			case 14:
				printf("E");
				break;
			case 15:
				printf("F"); 
				break;
			default:
				printf("%d",hexArray[i+1]);
				break;
			}		
			printf(" ");
	}	
	printf("\n");
}



int main(){
	
	FILE *myFile;

    myFile = fopen("input.txt", "r");
   
    char buffer[120];
    
    int size = 0;
	int count = 0;
	int i = 0;
	
    if(myFile == NULL){
    	printf("Invalid input!");
    	return 0;
	}
	printf("Ordering type: ");
	char type[60];
	scanf("%s",type);
	
	for(i =0; type[i]; i++){
		
		type[i] = tolower(type[i]);
	}
	fflush(stdin);
	printf("Floating point size : ");
	
	
	while(count == 0){ 
		scanf("%d",&size);
		if(size > 0 && size <=4)
			count = 1;
		else{
			printf("\nInvalid input please try again.\nFloating point size : ");
			count  = 0;
		} 
	}
	printf("\nResult is ready ! \n\n");
	
	while(fgets(buffer,120,myFile)){
		if(strchr(buffer,'u')!=NULL)
			forunsignedCase(buffer,type,size);
		else 
			forsignedCase(buffer,type,size);
			
			
	}
	
	return 0;
}

