//
//  MainWordCard.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 08.06.23.
//

import SwiftUI
import shared

struct MainWordCard: View {
    @ObservedObject var vm: MainWordView.MainWordViewModel
    let pasteboard = UIPasteboard.general
    
    @Environment(\.openURL) var openURL
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8.0) {
            HStack {
                Image(systemName: "text.book.closed.fill")
                    .font(.title2)
                    .foregroundColor(Color.germanPrimary)
                Spacer()
                Image(systemName: "questionmark.diamond.fill")
                    .font(.title2)
                    .foregroundColor(Color.germanPrimary)
            }
            Text("Your word is...")
            Text(vm.currentWord)
                .font(.title)
                .minimumScaleFactor(0.7)
                .padding(.vertical)
                .allowsTightening(true)
                .textSelection(.enabled)
            HStack {
                Button("DeepL") {
                    let url = vm.lookupWordOnline(service: SharingService.deepl)
                    openURL(URL(string: url) ?? URL(string: "https://www.deepl.com/translator")!)
                }
                .buttonStyle(.borderedProminent)
                Spacer()
                Button("Google Translate") {
                    let url = vm.lookupWordOnline(service: SharingService.googleTranslate)
                    openURL(URL(string: url) ?? URL(string: "https://translate.google.com/")!)
                }
                .buttonStyle(.borderedProminent)
                Spacer()
                Button("Copy") {
                    pasteboard.string = vm.currentWord
                }
                .buttonStyle(.bordered)
            }
        }
        .padding()
        .background(Color.secondaryBackground)
        .cornerRadius(15)
    }
}

struct MainWordCard_Previews: PreviewProvider {
    static var previews: some View {
        MainWordCard(vm: MainWordView.MainWordViewModel())
    }
}
