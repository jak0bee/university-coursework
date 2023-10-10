function [x, iter, norms] = jacobi_method(A, b, tol, maxIteration)
    n = length(A);
    x = zeros(n, 1);
    x_prev = x;
    iter = 0;
    norms = []; % Initialize an empty array to store the norms

    while iter < maxIteration
        for i = n:-1:1
            sum = b(i);
            for j = n:-1:1
                if j ~= i
                    sum = sum - A(i,j) * x_prev(j);
                end
            end
            x(i) = sum / A(i,i);
        end

        norm_diff = norm(x - x_prev, 2); % Calculate the norm difference
        norms = [norms, norm_diff]; % Append the norm difference to the array

        if norm_diff < tol
            break;
        end

        x_prev = x;
        iter = iter + 1;
    end
    if A(1, 2) == 3
    % Plotting the norm differences
    iterations = 1:length(norms);
    plot(iterations, norms);
    xlabel('Iteration');
    ylabel('Norm of Difference');
    title('Norm of Difference between Subsequent Iterations (Jacobi Method)');
    end

end
