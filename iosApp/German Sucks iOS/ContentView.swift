//
//  ContentView.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 06.06.23.
//

import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        Text(Greeting().greet())
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
