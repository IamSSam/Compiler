#include <stdio.h> 
int main() {
	long list1[] = {1,2,3,2,3};
	int _AD_size = 5;

	long result = 0;
	int _AD_i = 0;

	for( _AD_i = 0; _AD_i < _AD_size ; _AD_i++){
		result += list1[_AD_i];
	}

	printf("%d\n", result);

	return 0;
}