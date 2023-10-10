hx = @(x) cos(2*x)/(5-x);
D = 6;
N = 10000;
x = linspace(-pi, pi, N);
a0 = 1/pi * integral(hx, -pi, pi, 'ArrayValued', true);
display(a0);
a = zeros(1, D);
b = zeros(1, D);
for k = 1:D
    a(k) = 1/pi * trapz(x, hx(x).*cos(k*x));
    b(k) = 1/pi * trapz(x, hx(x).*sin(k*x));
end
display(a);
display(b);
%D = 1
s1 = @(x) a0/2 + sum(a.*cos((1:1).*x) + b.*sin((1:1).*x));
s1_vec = arrayfun(s1, x);
%D = 2
s2 = @(x) a0/2 + a(1)*cos(x) + b(1)*sin(x) + a(2)*cos(2*x) + b(2)*sin(2*x);
s2_vec = arrayfun(s2, x);
%D = 3
s3 = @(x) a0/2 + a(1)*cos(x) + b(1)*sin(x) + a(2)*cos(2*x) + b(2)*sin(2*x)+ a(3)*cos(3*x) + b(3)*sin(3*x);
s3_vec = arrayfun(s3,x);
%ploting the Sd for different D values
plot(x, s1_vec, '-');
hold on;
plot(x, s2_vec, '--', 'LineWidth', 2);
plot(x, s3_vec, '-');
hold off;
ylim([-0.3, 0.3]);
title('Sd(x) for different D values for h(x)');
legend('D=1', 'D=2', 'D=3');
errors = zeros(1, D);
for k = 1:D
sk = @(x) a0/2;
for i = 1:k
sk = @(x) sk(x) + a(i)*cos(i*x) + b(i)*sin(i*x);
end
errors(k) = sqrt(1/pi * integral(@(x) (hx(x) - sk(x)).^2, -pi, pi));
end
display(errors);