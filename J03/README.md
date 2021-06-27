## ex00 - Egg, Hen... or Human?
The truth is born in a dispute—let’s assume that each thread provides its own answer. The thread that has the last word is right. Inside the thread, public static void main(String args[]) method is executed. We need this thread to display all its responses at the end of program execution. Thus, the ultimate variant is as follows:
![egg, hen, human](https://user-images.githubusercontent.com/67025828/123535515-f55d5700-d72c-11eb-9786-fe926d260f38.png)

## ex01 - Egg, Hen, Egg, Hen...
Each thread can provide its answer only after another thread has done so. Let’s assume that the egg thread always answers first. To solve this task need explore Producer-Consumer model. Example:
![egg, hen, egg, hen](https://user-images.githubusercontent.com/67025828/123535624-9cda8980-d72d-11eb-95ec-32c705b884a0.png)

## ex02 - Real Multithreading
There is an array of integer values. Goal is to calculate the sum of array elements using several "summing" threads. Each thread computes a certain section inside the array. The number of elements in each section is constant, except for the last one (its size can differ upward or downward). The array is randomly generated each time. Array length and the number of threads are passed as command-line arguments.
![array sum](https://user-images.githubusercontent.com/67025828/123535702-2e49fb80-d72e-11eb-9acc-04d4c73c810d.png)

## ex03 - Too Many Threads...
Need to download a list of files from a network.To implement this functionality, we can obviously use multithreaded downloading where each thread loads a specific file. files_urls.txt contains list of file urls. 


## Comments:
1. Thread, Runnable, Synchronized were used for solving all tasks.
2. For compiling .class use javac, for run program use java. Or use IDE (IntelliJ IDEA).
