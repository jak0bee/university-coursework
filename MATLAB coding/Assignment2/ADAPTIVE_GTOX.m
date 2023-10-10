gx = @(x) (x < 0) .* 0 + (x >= 0) .* 1;
D = 6;
N = 10000;
x = linspace(-pi, pi, N);
a0 = 1/pi * integral(gx, -pi, pi, 'ArrayValued', true);

a = zeros(1, D);
b = zeros(1, D);
for k = 1:D
    fun_cos = @(t) gx(t) .* cos(k*t);
    fun_sin = @(t) gx(t) .* sin(k*t);

    if k == 1
        x_min = -pi;
    else
        x_min = acos(-a(k-1)/a(k))/k;
    end
    
    x_max = acos(-a(k)/a(k+1))/k;
    
    x_int = linspace(x_min, x_max, N);
    f_int = gx(x_int);
    cos_zeros = cos(k*x_int);

    h = (x_max - x_min)/(N-1);
    df = max(f_int) - min(f_int);
    dc = max(cos_zeros) - min(cos_zeros);
    eps_t = 1e-5;
    h = min(h, (eps_t/df)^(1/2)/dc);
    
    a(k) = 1/pi * h/2 * (fun_cos(x_min) + fun_cos(x_max) + 2*sum(fun_cos(x_int(2:end-1))));
    b(k) = 1/pi * h/2 * (fun_sin(x_min) + fun_sin(x_max) + 2*sum(fun_sin(x_int(2:end-1))));
end

s1 = @(x) a0/2 + sum(a.*cos((1:1).*x) + b.*sin((1:1).*x));
s1_vec = arrayfun(s1, x);

s2 = @(x) a0/2 + a(1)*cos(x) + b(1)*sin(x) + a(2)*cos(2*x) + b(2)*sin(2*x);
s2_vec = arrayfun(s2, x);

s3 = @(x) a0/2 + a(1)*cos(x) + b(1)*sin(x) + a(2)*cos(2*x) + b(2)*sin(2*x)+ a(3)*cos(3*x) + b(3)*sin(3*x);
s3_vec = arrayfun(s3,x);

figure;
plot(x, s1_vec, '-');
hold on;
plot(x, s2_vec, '--', 'LineWidth', 2);
plot(x, s3_vec, '-');
hold off;
ylim([-0.3, 0.3]);
title('Sd(x) for different D values for g(x) with adaptive trapezoid');
legend('D=1', 'D=2', 'D=3');
