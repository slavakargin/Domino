%The file means.txt contains the mean time to coupling
% for the tilings of 4N-by-4N boards with the tiles 
% of area 4.
% The N changed from 2 to 8 in this example

means = load('means.txt')
plot(log(means))
grid on
hold on 
(11 - 5)/7

%it looks that time to coupling is exponential 
%for this algorithm, E(T) = C e^(0.86 N) 

stds = load('STDs.txt')
plot(log(stds))
grid on
hold off
%The logarithm of the standard deviation is very close
%to the logarithm of the mean. 

%It would be interesting to compare these results
%with a similar data for the algorithm for 
%the usual domino (with 2-by-1 vertical and horizontal
%tiles).