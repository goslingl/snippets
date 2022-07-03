
* 模型实现
```
from hts.model.ar import AutoArimaModel, SarimaxModel
from hts.model.es import HoltWintersModel
from hts.model.p import FBProphetModel
```



* holt_winters
from statsmodels.tsa.holtwinters import ExponentialSmoothing
model = ExponentialSmoothing(endog=data, **kwargs)

https://www.statsmodels.org/stable/generated/statsmodels.tsa.holtwinters.ExponentialSmoothing.html#statsmodels.tsa.holtwinters.ExponentialSmoothing

freqstr, optional
The frequency of the time-series. A Pandas offset or ‘B’, ‘D’, ‘W’, ‘M’, ‘A’, or ‘Q’.


* auto_arima
from pmdarima import AutoARIMA
model = AutoARIMA(**kwargs)

https://pypi.org/project/pmdarima/
https://alkaline-ml.com/pmdarima/modules/generated/pmdarima.arima.AutoARIMA.html



* sarimax
from statsmodels.tsa.statespace.sarimax import SARIMAX
model = SARIMAX(endog=end, exog=ex, **kwargs)
https://www.statsmodels.org/stable/generated/statsmodels.tsa.statespace.sarimax.SARIMAX.html#statsmodels.tsa.statespace.sarimax.SARIMAX


* prophet
from fbprophet import Prophet
model = Prophet(growth=growth, **kwargs)
[指定粒度](https://facebook.github.io/prophet/docs/non-daily_data.html)

future = m.make_future_dataframe(periods=120, freq='MS')
fcst = m.predict(future)
fig = m.plot(fcst)

# 二阶段调和
hts/revision RevisionMethod.revise 调用

hts/functions.optimal_combination


Prophet.make_future_dataframe



