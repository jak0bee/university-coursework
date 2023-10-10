function [x, iter, norms] = gauss_seidel_method(A, b, tol, maxIteration)
    n = length(A);
    x = zeros(n, 1);
    x_prev = x;
    iter = 0;
    norms = [];
    


    while iter < maxIteration
        for i = n:-1:1
            sum = b(i);
            for j = n:-1:1
                if j ~= i
                    sum = sum - A(i,j) * x(j);
                end
            end
            x(i) = sum / A(i,i);
        end

        norm_diff = norm(x - x_prev, 2); 
        norms = [norms, norm_diff]; 

        if norm_diff < tol
            break;
        end

        x_prev = x;
        iter = iter + 1;
    end
        if A(1, 2) == 3
        iterations = 1:length(norms);
        plot(iterations, norms);
        xlabel('Iteration');
        ylabel('Norm of Difference');
        title('Norm of Difference between Subsequent Iterations (Gauss Seidel Method)');

    end
end
