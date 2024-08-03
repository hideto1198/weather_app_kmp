import SwiftUI
import Infrastructure
import Shared

struct ContentView: View {
    @ObservedObject var viewModel: TestViewModel
    var body: some View {
        ScrollView {
            switch viewModel.launches {
            case .loading:
                ProgressView()
                
            case .error(let error):
                Text("Error: \(error)")
                
            case .result(let launches):
                ForEach(launches, id: \.self) {
                    RocketLaunchRow(rocketLaunch: $0)
                }
            default:
                EmptyView()
            }
        }
        .task {
            await viewModel.loadLaunches()
        }
    }
}

struct RocketLaunchRow: View {
    var rocketLaunch: RocketLaunch

    var body: some View {
        HStack() {
            VStack(alignment: .leading, spacing: 10.0) {
                Text("\(rocketLaunch.missionName) - \(String(rocketLaunch.launchYear))").font(.system(size: 18)).bold()
                Text(launchText).foregroundColor(launchColor)
                Text("Launch year: \(String(rocketLaunch.launchYear))")
                Text("\(rocketLaunch.details ?? "")")
            }
            Spacer()
        }
    }
}

extension RocketLaunchRow {
    private var launchText: String {
        if let isSuccess = rocketLaunch.launchSuccess {
            return isSuccess.boolValue ? "Successful" : "Unsuccessful"
        } else {
            return "No data"
        }
    }

    private var launchColor: Color {
        if let isSuccess = rocketLaunch.launchSuccess {
            return isSuccess.boolValue ? Color.green : Color.red
        } else {
            return Color.gray
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: TestViewModel())
    }
}
