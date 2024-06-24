// package com.example.demo.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.example.demo.entity.Weather;
// import com.example.demo.repository.WeatherRepository;

// @Service
// //例外が起こった時に自動でロールバックしてくれる
// @Transactional
// public class WeatherService{
//   //使いたいクラスのインスタンス化をしてくれる
//   @Autowired
//   WeatherRepository weatherRepository;
  
//   /**
//    * レコードを全件取得する。
//    * @return
//    */
//   public List<Weather> findAllWeatherData(){
    
//     return weatherRepository.findAll();
//   }
// }