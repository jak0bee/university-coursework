A = [-11 1 -1; -2 5 1; 1 3 7];
b = [3; 4; 1];

% values of d to test
d_values = 1:5;

jacobiSolutions = zeros(length(b), length(d_values));
gsSolutions = zeros(length(b), length(d_values));
jacobiIterations = zeros(size(d_values));
gsIterations = zeros(size(d_values));

jacobiNorms = cell(size(d_values));
gsNorms = cell(size(d_values));

for i = 1:length(d_values)
    A(1,2) = d_values(i);
    A(2,3) = d_values(i);
    A(3,1) = d_values(i);

    [jacobiSolutions(:,i), jacobiIterations(i), jacobiNorms{i}] = jacobi_method(A, b, 1e-6, 1000);
    [gsSolutions(:,i), gsIterations(i), gsNorms{i}] = gauss_seidel_method(A, b, 1e-6, 1000);
end

disp('Jacobi method solutions for d=1,2,3,4,5:');
disp(jacobiSolutions);
disp('Jacobi method iterations for d=1,2,3,4,5:');
disp(jacobiIterations);
disp('Gauss-Seidel method solutions for d=1,2,3,4,5:');
disp(gsSolutions);
disp('Gauss-Seidel method iterations for d=1,2,3,4,5:');
disp(gsIterations);

% Plotting the norms for Jacobi method
figure;
subplot(2, 1, 1);
for i = 1:length(d_values)
    if d_values(i) == 3
        iterations_jacobi = 1:length(jacobiNorms{i});
        plot(iterations_jacobi, jacobiNorms{i}, 'b-', 'LineWidth', 2);
        hold on;
    end
end
xlabel('Iteration');
ylabel('Norm of Difference');
title('Norm of Difference between Subsequent Iterations (Jacobi Method)');
legend('d=3');

% Plotting the norms for Gauss-Seidel method
subplot(2, 1, 2);
for i = 1:length(d_values)
    if d_values(i) == 3
        iterations_gauss_seidel = 1:length(gsNorms{i});
        plot(iterations_gauss_seidel, gsNorms{i}, 'r-', 'LineWidth', 2);
        hold on;
    end
end
xlabel('Iteration');
ylabel('Norm of Difference');
title('Norm of Difference between Subsequent Iterations (Gauss-Seidel Method)');
legend('d=3');
