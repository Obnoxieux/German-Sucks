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
        switch UIDevice.current.userInterfaceIdiom {
            case .pad, .mac:
                //TODO: add layout for big screens
                EmptyView()
            case .phone:
                TabView {
                    NavigationStack {
                        MainWordView()
                    }
                        .tabItem {
                            Label("Word", systemImage: "book")
                                
                        }
                    Text("Not implemented yet")
                        .tabItem {
                            Label("Favorites", systemImage: "star.bubble")
                        }
                }
            default:
                EmptyView()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            //.preferredColorScheme(.dark)
    }
}
