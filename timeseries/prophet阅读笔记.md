prophet算法流程

* 数据预处理

* 分段线性函数

* 分段逻辑回归函数

* flat函数

* 模型拟合
    变点检测
    季度

原理

加性模型
y(t) = g(t) + s(t) + h(t) + εt

the advantage that 

it decomposes easily and accommodates new components as necessary,

 fit very quickly, either using backfitting or L-BFGS
 
 Unlike with ARIMA models, the measurements do not need to be regularly spaced, and we do not need to interpolate missing values e.g. from removing outliers.
 
 
趋势部分的模型
a saturating growth model, and a piecewise linear model.

C, k, m  (容量 增长率 位移)

三个超参数的函数化 （时间的函数）

k -> k(t): 变点检测， 
    S个变点(时刻$s_j$, j=1,...,S) 
    每个变点处的变化率（$\delta \in R^S$):$\delta_j$ 为  $s_j$时刻的变化率
    k和m的调整
    
线性模型 k 和 m的调整


变点如何产生
     选择大量的变点，使用先验 拉普拉斯分布 $\delta_j ~ Laplace(0, \tau)$ 参数 $\tau$ 直接控制模型的灵活性
     稀疏先验 $\delta$ 的调整，不影响主要增长率k；当 $\tau$ 降低到0， 拟合程度降低为标准的逻辑回归或线性回归
     
趋势预测的不确定性
    适用从数据中推断的方差 代替 变点的变化率的拉普拉斯分布的参数$\tao$ 
    在完整的贝叶斯框架中，适用层次先验获取后验；或者 使用 （rate scale parameter）的 最大似然估计 : $\lambda$
    
    未来的变点：使用随机抽样  
        j > T, $\delta_j ~ Laplace(0, \lambda) w.p. \frac{S}{T}
        (匹配 历史变点的平均频率)

    （假设未来会有和历史相同的变点 平均频率 和 重要性） - 这个假设太强，so 不期望 不确定区间 有准确的覆盖；但是 它们 是 不确定水平的 一个有用指标，特别是一个过拟合的指标：  当 $\tau$ 增大，模型拟合历史更灵活，训练误差会下降；然而 这种灵活性 向前 会产生更宽的不确定区间（why？）


模型拟合
  model {
  // Priors
  Listing 1: Example Stan code for our complete model.
  k ∼ normal(0, 5);
  m ∼ normal(0, 5);
  epsilon ∼ normal(0, 0.5);
  delta ∼ double_exponential(0, tau); 
  beta ∼ normal(0, sigma);
  
    // Logistic likelihood
  y ∼ normal(C ./ (1 + exp(-(k + A * delta) .* (t - (m + A * gamma)))) + X * beta, epsilon);
    // Linear likelihood
  y ∼ normal((k + A * delta) .* t + (m + A * gamma) + X * beta, sigma); }
  
  The parameters tau and sigma in Listing 1 are controls for the amount of regularization
  
  Regularization is important for both of these to avoid overfitting, however there likely is not enough historical data to select the best regularization parameters via cross-validation. We set default values that are appropriate for most forecasting problems, and when these parameters need to be optimized it happens with the analyst in the loop.
  
  
  
  The τ parameter is a single knob that can be turned to increase or decrease the trend flexibility
  
  and σ is a knob to increase or decrease the strength of the seasonality component
  
  
  
  
  
  


